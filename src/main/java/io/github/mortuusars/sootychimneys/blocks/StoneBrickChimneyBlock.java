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

public class StoneBrickChimneyBlock extends ChimneyBlock implements ISootyChimney{

    private static final Vector3f _particleOriginOffset = new Vector3f(0.5f, 1.2f, 0.5f);
    private static final Vector3f _particleMaxRandomOffset = new Vector3f(0.025f, 0.05f, 0.025f);
    private static final VoxelShape  _shape = Shapes.or(
            Block.box(5,0,5, 11,9,11),
            Block.box(4, 9, 4, 12, 16, 12));

    public StoneBrickChimneyBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
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
    public Block getCleanVariant() {
        return ModBlocks.STONE_BRICK_CHIMNEY.get();
    }

    @Override
    public Block getDirtyVariant() {
        return ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get();
    }
}
