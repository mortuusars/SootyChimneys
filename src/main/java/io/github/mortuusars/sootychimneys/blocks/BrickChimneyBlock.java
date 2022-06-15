package io.github.mortuusars.sootychimneys.blocks;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BrickChimneyBlock extends ChimneyBlock{

    private static final Vector3f _particleOriginOffset = new Vector3f(0.5f, 1.15f, 0.5f);
    private static final Vector3f _particleMaxRandomOffset = new Vector3f(0.15f, 0.1f, 0.15f);
    private static final VoxelShape _shape = Shapes.or(
            Block.box(1d, 0d, 1d, 15d,11d,15d),
            Block.box(0d, 11d, 0d, 16d,16d,16d));

    public BrickChimneyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
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
        return 2f;
    }

    @Override
    public float getSmokeSpeed() {
        return 1.2f;
    }
}
