package io.github.mortuusars.sootychimneys.blocks;

import com.mojang.math.Vector3f;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BrickChimneyBlock extends ChimneyBlock implements ISootyChimney{
    private static final Vector3f _particleOriginOffset = new Vector3f(0.5f, 1f, 0.5f);
    private static final Vector3f _particleMaxRandomOffset = new Vector3f(0.25f, 0.1f, 0.25f);
    private static final VoxelShape _shape = Shapes.or(
            Block.box(1d, 0d, 1d, 15d,11d,15d),
            Block.box(0d, 11d, 0d, 16d,16d,16d));

    public BrickChimneyBlock(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public Block getCleanVariant() {
        return ModBlocks.BRICK_CHIMNEY.get(); //can't return 'this' here because Dirty variant will have wrong clean block, and 'this' breaks scraping with axe
    }

    @NotNull
    @Override
    public Block getDirtyVariant() {
        return ModBlocks.DIRTY_BRICK_CHIMNEY.get();
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return _shape;
    }

    @Override
    public Vector3f getParticleOriginOffset() {
        return _particleOriginOffset;
    }

    @Override
    public Vector3f getParticleMaxRandomOffset() {
        return _particleMaxRandomOffset;
    }

    @Override
    public float getSmokeIntensity() {
        return 1f;
    }

    @Override
    public float getSmokeSpeed() {
        return 1.2f;
    }

}
