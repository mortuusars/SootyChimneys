package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.PlatformSpecific;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import io.github.mortuusars.sootychimneys.data.wind.WindData;
import io.github.mortuusars.sootychimneys.data.smoke.SmokeProperties;
import io.github.mortuusars.sootychimneys.data.Chimney;
import io.github.mortuusars.sootychimneys.data.ChimneyType;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.result.ChanceResult;
import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ChimneyBlock extends Block implements EntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");
    public static final BooleanProperty STACKED = BooleanProperty.create("stacked");

    protected final ChimneyType type;
    protected final Chimney.State state;

    public ChimneyBlock(Properties properties, Chimney.State state, ChimneyType type) {
        super(properties);
        this.state = state;
        this.type = type;

        this.registerDefaultState(defaultBlockState()
                .setValue(LIT, true)
                .setValue(BLOCKED, false)
                .setValue(STACKED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT)
                .add(BLOCKED)
                .add(STACKED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(ChimneyBlock.STACKED) ? getStackedShape() : getRegularShape();
    }

    public Chimney.State getChimneyState() {
        return state;
    }

    public ChimneyType getType() {
        return type;
    }

    public @NotNull VoxelShape getRegularShape() {
        return getType().shape().regular();
    }

    public @NotNull VoxelShape getStackedShape() {
        return getType().shape().stacked();
    }

    public boolean isClean() {
        return state == Chimney.State.CLEAN;
    }

    public boolean isDirty() {
        return state == Chimney.State.DIRTY;
    }

    /**
     * Expects a ChimneyBlock block state. May fail if not Chimney.
     */
    public boolean shouldEmitSmoke(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(LIT)
                && !blockState.getValue(BLOCKED)
                && !blockState.getValue(STACKED)
                && !level.getBlockState(pos.above()).is(SootyChimneys.Tags.Blocks.SMOKE_BLOCKING);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                                        Player player, BlockHitResult hitResult) {
        if (!player.getMainHandItem().isEmpty()) {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }

        boolean newBlockedValue = !state.getValue(BLOCKED);

        if (!level.isClientSide && level.setBlock(pos, state.setValue(BLOCKED, newBlockedValue), Block.UPDATE_ALL)) {
            RandomSource random = level.getRandom();

            level.playSound(null, pos, newBlockedValue ? SoundEvents.LANTERN_FALL : SoundEvents.LANTERN_HIT, SoundSource.BLOCKS,
                    0.8f, 0.85f + random.nextFloat() * 0.05f);

            Vector3f particleOrigin = getType().smokeProperties().getParticleOrigin();
            for (int i = 0; i < random.nextInt(5); i++) {
                ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE,
                        pos.getX() + particleOrigin.x(), pos.getY() + particleOrigin.y() - 0.1, pos.getZ() + particleOrigin.z(),
                        1, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, 0);
            }

            String messageTranslationKey = "message.sootychimneys." + (newBlockedValue ? "blocked" : "open");
            player.displayClientMessage(Component.translatable(messageTranslationKey), true);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                                       Player player, InteractionHand hand, BlockHitResult hitResult) {
        //noinspection ConstantValue
        if (!(state.getBlock() instanceof ChimneyBlock chimney)
                || !chimney.isDirty()
                || !PlatformSpecific.canBeUsedToScrapeSoot(stack)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }

        BlockState cleanBlockState = Chimney.getCleanBlock(this).withPropertiesOf(state);
        level.setBlock(pos, cleanBlockState, Block.UPDATE_ALL);

        if (level instanceof ServerLevel serverLevel) {
            player.awardStat(SootyChimneys.Stats.SOOT_SCRAPED);

            Optional<Supplier<List<ItemStack>>> scrapingResult = getScrapedItems(state, serverLevel);

            scrapingResult.ifPresent(items -> {
                List<ItemStack> itemStacks = items.get();
                // Offset item spawning pos, depending on clicked face, to spawn items closer to the player.
                // Items shooting in opposite direction is not fun.
                Vec3i faceNormal = hitResult.getDirection().getNormal();
                Vector3f itemSpawnPosition = new Vector3f(pos.getX() + 0.5f + faceNormal.getX() * 0.65f,
                        pos.getY() + 0.6f + faceNormal.getY() * 0.65f,
                        pos.getZ() + 0.5f + faceNormal.getZ() * 0.65f);

                spawnSootScrapingItems(itemSpawnPosition, serverLevel, itemStacks);
            });

            stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS);
        } else {
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS);
            chimney.spawnSootParticles(level, pos, false);
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        updateState(state, level, pos);
    }

    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos fromPos, boolean isMoving) {
        updateState(state, level, pos);
    }

    protected void updateState(BlockState state, Level level, BlockPos pos) {
        boolean stacked = level.getBlockState(pos.above()).getBlock() instanceof ChimneyBlock;

        if (stacked && state.getBlock() instanceof ChimneyBlock chimney && chimney.isDirty()) {
            state = Chimney.getCleanBlock(chimney).defaultBlockState();
            chimney.spawnSootParticles(level, pos, true);
        }

        level.setBlock(pos, state
                        .setValue(LIT, !level.hasNeighborSignal(pos))
                        .setValue(STACKED, stacked),
                Block.UPDATE_ALL);
    }

    public ParticleOptions getParticle(BlockState state, Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return (stateBelow.getBlock() instanceof ChimneyBlock && stateBelow.getValue(STACKED))
                || stateBelow.is(SootyChimneys.Tags.Blocks.SMOKE_BOOSTING)
                ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
    }

    public void emitParticle(Level level, double x, double y, double z, ParticleOptions particleType) {
        if (!level.isClientSide)
            return;

        SmokeProperties smokeProperties = getType().smokeProperties();

        RandomSource random = level.getRandom();

        if (random.nextFloat() > smokeProperties.getIntensity())
            return;

        Vector3f particleOffset = smokeProperties.getParticleOrigin();
        x += particleOffset.x() - 0.5;
        y += particleOffset.y() - 0.5;
        z += particleOffset.z() - 0.5;

        WindData wind = Wind.getWind();
        float strength = wind.getAdjustedStrength();
        double xSpeed = wind.getXCoordinate() * strength;
        double ySpeed = 0.05d * smokeProperties.getSpeed();
        double zSpeed = wind.getYCoordinate() * strength;

        xSpeed += ((random.nextFloat() * strength) - (strength / 2f)) * 0.2f;
        zSpeed += ((random.nextFloat() * strength) - (strength / 2f)) * 0.2f;

        Vector3f particleSpread = smokeProperties.getParticleSpread();

        int maxParticles = ((int) (4 * Math.max(smokeProperties.getIntensity(), 0.5f)));

        for (int i = 0; i < random.nextInt(maxParticles); i++) {
            level.addAlwaysVisibleParticle(particleType, true,
                    RandomOffset.offset(x, particleSpread.x()),
                    RandomOffset.offset(y, particleSpread.y()),
                    RandomOffset.offset(z, particleSpread.z()),
                    xSpeed, ySpeed, zSpeed);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ChimneyBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide() && type == SootyChimneys.BlockEntityTypes.CHIMNEY.get()
                ? ChimneyBlockEntity::particleTick : null;
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getBlock() instanceof ChimneyBlock chimney && chimney.isClean();
    }

    @Override
    public void randomTick(BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (blockState.getBlock() instanceof ChimneyBlock chimney
                && chimney.isClean()
                && shouldEmitSmoke(blockState, level, pos)
                && random.nextDouble() < Config.Common.DIRTY_CHANCE.get()) {
            level.setBlock(pos, Chimney.getDirtyBlock(chimney).defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    protected Optional<Supplier<List<ItemStack>>> getScrapedItems(BlockState state, ServerLevel level) {
        SingleRecipeInput input = new SingleRecipeInput(new ItemStack(state.getBlock().asItem()));

        Optional<RecipeHolder<SootScrapingRecipe>> recipeOptional = level.getRecipeManager()
                .getRecipeFor(SootyChimneys.RecipeTypes.SOOT_SCRAPING.get(), input, level);

        return recipeOptional.map(recipeHolder -> () -> {
            SootScrapingRecipe recipe = recipeHolder.value();
            List<ItemStack> items = new ArrayList<>();
            for (ChanceResult result : recipe.results()) {
                ItemStack itemStack = result.rollOutput(level.getRandom());
                if (!itemStack.isEmpty())
                    items.add(itemStack);
            }
            return items;
        });
    }

    public void spawnSootScrapingItems(Vector3f pos, ServerLevel level, List<ItemStack> items) {
        for (ItemStack itemStack : items) {
            Containers.dropItemStack(level, pos.x(), pos.y(), pos.z(), itemStack);
        }
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level instanceof Level lvl && state.getBlock() instanceof ChimneyBlock chimney && chimney.isDirty())
            chimney.spawnSootParticles(lvl, pos, false);
    }

    public void spawnSootParticles(Level level, BlockPos pos, boolean serverSide) {
        RandomSource random = level.getRandom();
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        for (int i = 0; i < random.nextInt(12, 20); i++) {
            if (serverSide && level instanceof ServerLevel serverLevel)
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                        RandomOffset.offset(x, 0.8f),
                        RandomOffset.offset(y, 0.8f),
                        RandomOffset.offset(z, 0.8f),
                        1, 0, 0, 0, 0);
            else {
                level.addParticle(ParticleTypes.LARGE_SMOKE,
                        RandomOffset.offset(x, 0.8f),
                        RandomOffset.offset(y, 0.8f),
                        RandomOffset.offset(z, 0.8f),
                        0, 0, 0);
            }
        }
    }
}
