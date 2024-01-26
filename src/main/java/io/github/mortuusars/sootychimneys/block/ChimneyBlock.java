package io.github.mortuusars.sootychimneys.block;

import com.mojang.math.Vector3f;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.config.Config;
import io.github.mortuusars.sootychimneys.core.Smoke;
import io.github.mortuusars.sootychimneys.core.Wind;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.loot.ModLootTables;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ToolActionIngredient;
import io.github.mortuusars.sootychimneys.setup.ModBlockEntities;
import io.github.mortuusars.sootychimneys.setup.ModRecipeTypes;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ChimneyBlock extends Block implements EntityBlock {
    public enum Type {
        CLEAN,
        DIRTY
    }

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");
    public static final BooleanProperty STACKED = BooleanProperty.create("stacked");

    protected final SootyChimneys.Chimney variant;
    protected final Type type;

    public ChimneyBlock(Properties properties, SootyChimneys.Chimney variant, Type type) {
        super(properties);
        this.variant = variant;
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

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(ChimneyBlock.STACKED) ? variant.getStackedShape() : variant.getDefaultShape();
    }

    public SootyChimneys.Chimney getVariant() {
        return variant;
    }

    public Type getType() {
        return type;
    }

    public Smoke getSmokeProperties() {
        return variant.getSmoke();
    }

    public boolean isClean() {
        return type == Type.CLEAN;
    }

    public boolean isDirty() {
        return type == Type.DIRTY;
    }

    /**
     * Expects a ChimneyBlock block state. May fail if not Chimney.
     */
    public boolean shouldEmitSmoke(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(LIT)
                && !blockState.getValue(BLOCKED)
                && !blockState.getValue(STACKED)
                && !level.getBlockState(pos.above()).is(ModTags.Blocks.SMOKE_BLOCKING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
        if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
            return InteractionResult.PASS;

        boolean newBlockedValue = !state.getValue(BLOCKED);

        if (level.isClientSide) {
            String messageTranslationKey = "message.sootychimneys." + (newBlockedValue ? "blocked" : "open");
            player.displayClientMessage(Component.translatable(messageTranslationKey), true);
        } else {
            Vector3f particleOrigin = getSmokeProperties().getParticleOrigin();
            RandomSource random = level.getRandom();
            for (int i = 0; i < random.nextInt(5); i++) {
                ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE,
                        pos.getX() + particleOrigin.x(), pos.getY() + particleOrigin.y() - 0.1, pos.getZ() + particleOrigin.z(),
                        1, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, 0);
            }
            level.playSound(null, pos, newBlockedValue ? SoundEvents.LANTERN_FALL : SoundEvents.LANTERN_HIT, SoundSource.BLOCKS,
                    0.8f, 0.85f + random.nextFloat() * 0.05f);
            level.setBlock(pos, state.setValue(BLOCKED, newBlockedValue), Block.UPDATE_ALL);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        updateState(state, level, pos);
    }

    @SuppressWarnings("deprecation")
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos fromPos, boolean isMoving) {
        updateState(state, level, pos);
    }

    protected void updateState(BlockState state, Level level, BlockPos pos) {
        boolean stacked = level.getBlockState(pos.above()).getBlock() instanceof ChimneyBlock;

        if (stacked && state.getBlock() instanceof ChimneyBlock chimney && chimney.isDirty()) {
            state = chimney.getVariant().getCleanBlock().defaultBlockState();
            chimney.spawnSootParticles(level, pos, true);
        }

        level.setBlock(pos, state
                        .setValue(LIT, !level.hasNeighborSignal(pos))
                        .setValue(STACKED, stacked),
                Block.UPDATE_ALL);
    }

    public ParticleOptions getParticle(BlockState state, Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return (stateBelow.getBlock() instanceof ChimneyBlock && stateBelow.getValue(STACKED)) || stateBelow.is(ModTags.Blocks.SMOKE_BOOSTING) ?
                ParticleTypes.CAMPFIRE_SIGNAL_SMOKE
                : ParticleTypes.CAMPFIRE_COSY_SMOKE;
    }

    public void emitParticle(Level level, double x, double y, double z, ParticleOptions particleType) {
        if (!level.isClientSide)
            return;

        RandomSource random = level.getRandom();

        if (random.nextFloat() > getSmokeProperties().getIntensity())
            return;

        Vector3f particleOffset = getSmokeProperties().getParticleOrigin();
        x += particleOffset.x() - 0.5;
        y += particleOffset.y() - 0.5;
        z += particleOffset.z() - 0.5;

        Wind wind = WindGetter.getWind();
        double windStrengthModifier = Config.WIND_STRENGTH_MULTIPLIER.get();
        double xSpeed = (wind.getXCoordinate() * wind.getStrength()) * windStrengthModifier;
        double zSpeed = (wind.getYCoordinate() * wind.getStrength()) * windStrengthModifier;
        double ySpeed = 0.05d * getSmokeProperties().getSpeed();

        Vector3f particleSpread = getSmokeProperties().getParticleSpread();

        int maxParticles = ((int) (4 * Math.max(getSmokeProperties().getIntensity(), 0.5f)));

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
        return level.isClientSide() && type == ModBlockEntities.CHIMNEY_BLOCK_ENTITY.get()
                ? ChimneyBlockEntity::particleTick : null;
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getBlock() instanceof ChimneyBlock chimney && chimney.isClean();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (blockState.getBlock() instanceof ChimneyBlock sootyChimney
                && sootyChimney.isClean()
                && shouldEmitSmoke(blockState, level, pos)
                && random.nextDouble() < Config.DIRTY_CHANCE.get()) {
            level.setBlock(pos, sootyChimney.getVariant().getDirtyBlock().defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (!(state.getBlock() instanceof ChimneyBlock sootyChimney) || !sootyChimney.isDirty())
            return null;

        Level level = context.getLevel();
        Optional<Supplier<List<ItemStack>>> scrapingResult = getScrapingResult(state, level, context.getItemInHand(), toolAction);
        if (scrapingResult.isEmpty())
            return null;

        if (!simulate) {
            if (level instanceof ServerLevel serverLevel) {
                List<ItemStack> loot = scrapingResult.get().get();

                BlockPos pos = context.getClickedPos();
                // Offset item spawning pos, depending on clicked face, to spawn items closer to the player.
                // Items shooting in opposite direction is not fun.
                Vec3i faceNormal = context.getClickedFace().getNormal();
                Vector3f itemSpawnPosition = new Vector3f(pos.getX() + 0.5f + faceNormal.getX() * 0.65f,
                        pos.getY() + 0.6f + faceNormal.getY() * 0.65f,
                        pos.getZ() + 0.5f + faceNormal.getZ() * 0.65f);

                spawnSootScrapingItems(itemSpawnPosition, serverLevel, loot);
            }
            else {
                sootyChimney.spawnSootParticles(level, context.getClickedPos(), false);
            }
        }

        return sootyChimney.getVariant().getCleanBlock().withPropertiesOf(state);
    }

    protected Optional<Supplier<List<ItemStack>>> getScrapingResult(BlockState state, Level level, ItemStack tool, ToolAction action) {
        if (Config.USE_LOOT_TABLES_FOR_SCRAPING.get()) {
            if (action != ToolActions.AXE_SCRAPE || !tool.canPerformAction(ToolActions.AXE_SCRAPE))
                return Optional.empty();

            return level instanceof ServerLevel serverLevel ?
                    Optional.of(() -> ModLootTables.getSootScrapingLootFor(state, serverLevel))
                    : Optional.of(Collections::emptyList);
        }

        SimpleContainer simpleContainer = new SimpleContainer(new ItemStack(state.getBlock().asItem()), tool);
        Optional<SootScrapingRecipe> recipeOptional = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.SOOT_SCRAPING.get(), simpleContainer, level);

        if (recipeOptional.isEmpty() || (recipeOptional.get().getTool() instanceof ToolActionIngredient toolActionIngredient
            && !toolActionIngredient.toolAction.equals(action)))
            return Optional.empty();

        if (level.isClientSide)
            return Optional.of(Collections::emptyList);
        else {
            return Optional.of(() -> {
                SootScrapingRecipe recipe = recipeOptional.get();
                List<ItemStack> items = new ArrayList<>();
                for (ChanceResult result : recipe.getResults()) {
                    ItemStack itemStack = result.rollOutput(level.getRandom());
                    if (!itemStack.isEmpty())
                        items.add(itemStack);
                }
                return items;
            });
        }
    }

    public void spawnSootScrapingItems(Vector3f pos, ServerLevel level, List<ItemStack> items) {
        for (ItemStack itemStack : items) {
            ItemEntity entity = new ItemEntity(level, pos.x(), pos.y(), pos.z(), itemStack);
            entity.spawnAtLocation(itemStack);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean isDestroyed = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        if (isDestroyed && state.getBlock() instanceof ChimneyBlock sootyChimney && sootyChimney.isDirty())
            sootyChimney.spawnSootParticles(level, pos, false);
        return isDestroyed;
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
                        1, 0,0,0, 0);
            else {
                level.addParticle(ParticleTypes.LARGE_SMOKE,
                        RandomOffset.offset(x, 0.8f),
                        RandomOffset.offset(y, 0.8f),
                        RandomOffset.offset(z, 0.8f),
                        0,0,0);
            }
        }
    }
}
