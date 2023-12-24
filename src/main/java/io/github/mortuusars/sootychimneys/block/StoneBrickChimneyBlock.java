package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoneBrickChimneyBlock extends ChimneyBlock implements ISootyChimney {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4,0,4, 12,11,12),
            Block.box(3, 11, 3, 13, 16, 13));

    private static final VoxelShape STACKED_SHAPE = Block.box(4,0,4, 12,16,12);

    public StoneBrickChimneyBlock() {
        super(new ChimneySmokeProperties(0.5f, 1.2f, 0.5f, 0.025f, 0.05f, 0.025f)
                .setIntensity(0.5f),
            BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops());
    }

    @Override
    protected VoxelShape getDefaultShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getStackedShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return STACKED_SHAPE;
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
