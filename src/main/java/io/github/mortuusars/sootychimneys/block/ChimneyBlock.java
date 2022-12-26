package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.core.Wind;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.loot.ModLootTables;
import io.github.mortuusars.sootychimneys.setup.ModBlockEntities;
import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
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
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

/**
 * Base block for chimneys. Contains common functionality.
 */
@SuppressWarnings({"NullableProblems", "deprecation"})
public abstract class ChimneyBlock extends Block implements EntityBlock {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");

    private final ChimneySmokeProperties smokeProperties;

    public ChimneyBlock(ChimneySmokeProperties smokeProperties, Properties properties) {
        super(properties);
        this.smokeProperties = smokeProperties;

        this.registerDefaultState(defaultBlockState()
                .setValue(LIT, true)
                .setValue(BLOCKED, false));
    }

    public ChimneySmokeProperties getSmokeProperties() {
        return this.smokeProperties;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder
            .add(LIT)
            .add(BLOCKED);
    }

    /**
     * Expects a ChimneyBlock block state. May fail if not Chimney.
     */
    public boolean isEmittingSmoke(BlockState blockState) {
        return blockState.getValue(LIT) && !blockState.getValue(BLOCKED);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
            return InteractionResult.PASS;

        boolean newBlockedValue = !state.getValue(BLOCKED);

        if (level.isClientSide) {
            String messageTranslationKey = "message.sootychimneys." + (newBlockedValue ? "blocked" : "open");

            player.displayClientMessage(Component.translatable(messageTranslationKey), true);
        }
        else {
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

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(pLevel.isClientSide)
            return;

        boolean hasRedstoneSignal = pLevel.hasNeighborSignal(pPos);
        if (pState.getValue(LIT) == hasRedstoneSignal)
            pLevel.setBlock(pPos, pState.setValue(LIT, !hasRedstoneSignal), Block.UPDATE_ALL);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(LIT, !pContext.getLevel().hasNeighborSignal(pContext.getClickedPos()));
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide)
            pLevel.setBlock(pPos, pState.setValue(LIT, !pLevel.hasNeighborSignal(pPos)), Block.UPDATE_CLIENTS);
    }

    @SuppressWarnings("unused")
    public void emitParticles(Level level, BlockPos pos, BlockState state){
        RandomSource random = level.getRandom();

        if (random.nextFloat() > getSmokeProperties().getIntensity())
            return;

        Vector3f particleOffset = getSmokeProperties().getParticleOrigin();
        float x = pos.getX() + particleOffset.x();
        float y = pos.getY() + particleOffset.y();
        float z = pos.getZ() + particleOffset.z();

        Wind wind = WindGetter.getWind();
        float windStrengthModifier = 0.1f;
        double xSpeed = (wind.getXCoordinate() * wind.getStrength()) * windStrengthModifier;
        double zSpeed = (wind.getYCoordinate() * wind.getStrength()) * windStrengthModifier;
        double ySpeed = 0.05d * getSmokeProperties().getSpeed();

        Vector3f particleSpread = getSmokeProperties().getParticleSpread();

        int maxParticles = ((int) (4 * Math.max(getSmokeProperties().getIntensity(), 0.5f)));

        for (int i = 0; i < random.nextInt(maxParticles); i++) {
            level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true,
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
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() && isEmittingSmoke(state) && type == ModBlockEntities.CHIMNEY_BLOCK_ENTITY.get()
                ? ChimneyBlockEntity::particleTick : null;
    }

    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getBlock() instanceof ISootyChimney chimney && chimney.isClean() && isEmittingSmoke(blockState);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource random) {
        if (blockState.getBlock() instanceof ISootyChimney sootyChimney
                && sootyChimney.isClean()
                && isEmittingSmoke(blockState)
                && random.nextDouble() < CommonConfig.DIRTY_CHANCE.get()){
            level.setBlock(pos, sootyChimney.getDirtyVariant().defaultBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        Block block = state.getBlock();
        if (toolAction == ToolActions.AXE_SCRAPE && block instanceof ISootyChimney sootyChimney && sootyChimney.isDirty()){
            sootyChimney.makeSootParticles(context.getLevel(), context.getClickedPos());

            if (!context.getLevel().isClientSide){
                ServerLevel level = (ServerLevel)context.getLevel();

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

    public void spawnSootScrapingItems(Vector3f pos, ServerLevel level, List<ItemStack> items){
        for (ItemStack itemStack : items) {
            ItemEntity entity = new ItemEntity(level, pos.x(), pos.y(), pos.z(), itemStack);
            entity.spawnAtLocation(itemStack);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean isDestroyed = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        if (isDestroyed && state.getBlock() instanceof ISootyChimney sootyChimney && sootyChimney.isDirty())
            sootyChimney.makeSootParticles(level, pos);
        return isDestroyed;
    }
}
