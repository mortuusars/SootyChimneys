package io.github.mortuusars.sootychimneys.blocks;

import com.mojang.math.Vector3f;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.core.Wind;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.setup.ModBlockEntities;
import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
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
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * Base block for chimneys. Contains common functionality.
 */
public abstract class ChimneyBlock extends Block implements EntityBlock {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public ChimneyBlock(Properties properties) {
        super(properties
                .randomTicks()
                .strength(2f, 2f)
                .requiresCorrectToolForDrops());
        this.registerDefaultState(defaultBlockState()
                .setValue(LIT, true));
    }

    public Vector3f getParticleOriginOffset(){
        return new Vector3f(0.5f, 1f, 0.5f);
    }

    public Vector3f getParticleMaxRandomOffset(){
        return new Vector3f(0.15f, 0.1f, 0.15f);
    }

    public float getSmokeIntensity(){
        return 1f;
    }

    public float getSmokeSpeed(){
        return 1f;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(LIT);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);

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

    public static void emitParticles(Level level, BlockPos pos, BlockState state){
        Random random = level.getRandom();

        ChimneyBlock chimney = ((ChimneyBlock)state.getBlock());

        Vector3f particleOffset = chimney.getParticleOriginOffset();
        float x = pos.getX() + particleOffset.x();
        float y = pos.getY() + particleOffset.y();
        float z = pos.getZ() + particleOffset.z();

        Wind wind = WindGetter.getWind();
        float windStrengthModifier = 0.1f;
        double xSpeed = (wind.getXCoordinate() * wind.getStrength()) * windStrengthModifier;
        double zSpeed = (wind.getYCoordinate() * wind.getStrength()) * windStrengthModifier;
        double ySpeed = 0.05d * chimney.getSmokeSpeed();

                Vector3f maxRandomOffset = chimney.getParticleMaxRandomOffset();

        for (int i = 0; i < random.nextInt((int)(2 * chimney.getSmokeIntensity())); i++) {
            level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true,
                    RandomOffset.offset(x, maxRandomOffset.x(), random),
                    RandomOffset.offset(y, maxRandomOffset.y(), random),
                    RandomOffset.offset(z, maxRandomOffset.z(), random),
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
        if (level.isClientSide() && state.getValue(LIT))
            return type == ModBlockEntities.CHIMNEY_BLOCK_ENTITY.get() ? ChimneyBlockEntity::particleTick : null;

        return null;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pState.getBlock() instanceof ISootyChimney sootyChimney
                && sootyChimney.isClean()
                && pState.getValue(LIT)
                && pRandom.nextDouble(0.00001d, 1.0d) < CommonConfig.DIRTY_CHANCE.get()){
            pLevel.setBlock(pPos, sootyChimney.getDirtyVariant().defaultBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        Block block = state.getBlock();
        if (toolAction == ToolActions.AXE_SCRAPE && block instanceof ISootyChimney sootyChimney && sootyChimney.isDirty()){
            sootyChimney.makeSootParticles(context.getLevel(), context.getClickedPos());
            return sootyChimney.getCleanVariant().defaultBlockState();
        }
        return null;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean isDestroyed = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        if (isDestroyed && state.getBlock() instanceof ISootyChimney sootyChimney && sootyChimney.isDirty())
            sootyChimney.makeSootParticles(level, pos);
        return isDestroyed;
    }
}
