package io.github.mortuusars.sootychimneys.block;

import com.mojang.math.Vector3f;
import io.github.mortuusars.sootychimneys.config.Config;
import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.core.Wind;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.loot.ModLootTables;
import io.github.mortuusars.sootychimneys.setup.ModBlockEntities;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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

import java.util.List;

/**
 * Base block for chimneys. Contains common functionality.
 */
public abstract class ChimneyBlock extends Block implements EntityBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");
    public static final BooleanProperty STACKED = BooleanProperty.create("stacked");

    private final ChimneySmokeProperties smokeProperties;

    public ChimneyBlock(ChimneySmokeProperties smokeProperties, Properties properties) {
        super(properties);
        this.smokeProperties = smokeProperties;

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
        return state.getValue(ChimneyBlock.STACKED) ? getStackedShape(state, level, pos, context) : getDefaultShape(state, level, pos, context);
    }

    protected abstract VoxelShape getDefaultShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);
    protected abstract VoxelShape getStackedShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);

    public ChimneySmokeProperties getSmokeProperties() {
        return this.smokeProperties;
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
        boolean stacked = level.getBlockState(pos.above()).is(ModTags.Blocks.CHIMNEYS);

        if (stacked && state.getBlock() instanceof ISootyChimney chimney && chimney.isDirty()) {
            state = chimney.getCleanVariant().defaultBlockState();
            chimney.spawnSootParticles(level, pos, true);
        }

        level.setBlock(pos, state
                        .setValue(LIT, !level.hasNeighborSignal(pos))
                        .setValue(STACKED, stacked),
                Block.UPDATE_ALL);
    }

    @SuppressWarnings("unused")
    public void emitParticlesOnClient(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide)
            return;

        RandomSource random = level.getRandom();

        if (random.nextFloat() > getSmokeProperties().getIntensity())
            return;

        Vector3f particleOffset = getSmokeProperties().getParticleOrigin();
        float x = pos.getX() + particleOffset.x();
        float y = pos.getY() + particleOffset.y();
        float z = pos.getZ() + particleOffset.z();

        Wind wind = WindGetter.getWind();
        double windStrengthModifier = Config.WIND_STRENGTH_MULTIPLIER.get();
        double xSpeed = (wind.getXCoordinate() * wind.getStrength()) * windStrengthModifier;
        double zSpeed = (wind.getYCoordinate() * wind.getStrength()) * windStrengthModifier;
        double ySpeed = 0.05d * getSmokeProperties().getSpeed();

        Vector3f particleSpread = getSmokeProperties().getParticleSpread();

        int maxParticles = ((int) (4 * Math.max(getSmokeProperties().getIntensity(), 0.5f)));

        for (int i = 0; i < random.nextInt(maxParticles); i++) {
            SimpleParticleType particleType = level.getBlockState(pos.below()).is(ModTags.Blocks.CHIMNEYS) ?
                    ParticleTypes.CAMPFIRE_SIGNAL_SMOKE
                    : ParticleTypes.CAMPFIRE_COSY_SMOKE;

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
        return blockState.getBlock() instanceof ISootyChimney chimney && chimney.isClean();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (blockState.getBlock() instanceof ISootyChimney sootyChimney
                && sootyChimney.isClean()
                && shouldEmitSmoke(blockState, level, pos)
                && random.nextDouble() < Config.DIRTY_CHANCE.get()) {
            level.setBlock(pos, sootyChimney.getDirtyVariant().defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        Block block = state.getBlock();
        if (toolAction == ToolActions.AXE_SCRAPE && block instanceof ISootyChimney sootyChimney && sootyChimney.isDirty()) {
            sootyChimney.spawnSootParticles(context.getLevel(), context.getClickedPos(), false);

            if (!context.getLevel().isClientSide) {
                ServerLevel level = (ServerLevel) context.getLevel();

                Item item = block.asItem();

                BlockPos pos = context.getClickedPos();
                // Offset item spawning pos, depending on clicked face, to spawn items closer to the player.
                // Items shooting in opposite direction is not fun.
                Vec3i faceNormal = context.getClickedFace().getNormal();
                Vector3f itemSpawnPosition = new Vector3f(pos.getX() + 0.5f + faceNormal.getX() * 0.65f,
                        pos.getY() + 0.6f + faceNormal.getY() * 0.65f,
                        pos.getZ() + 0.5f + faceNormal.getZ() * 0.65f);

                List<ItemStack> items = ModLootTables.getSootScrapingLootFor(state, level);
                spawnSootScrapingItems(itemSpawnPosition, level, items);
            }

            return sootyChimney.getCleanVariant().withPropertiesOf(state);
        }
        return null;
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
        if (isDestroyed && state.getBlock() instanceof ISootyChimney sootyChimney && sootyChimney.isDirty())
            sootyChimney.spawnSootParticles(level, pos, false);
        return isDestroyed;
    }
}
