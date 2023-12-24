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
import org.jetbrains.annotations.NotNull;

public class BrickChimneyBlock extends ChimneyBlock implements ISootyChimney {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(1d, 0d, 1d, 15d,11d,15d),
            Block.box(0d, 11d, 0d, 16d,16d,16d));

    private static final VoxelShape STACKED_SHAPE = Block.box(1d, 0d, 1d, 15d, 16d, 15d);

    public BrickChimneyBlock() {
        super(new ChimneySmokeProperties(0.5f, 1f, 0.5f, 0.25f, 0.1f, 0.25f)
                        .setSpeed(1.2f),
                BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                        .sound(SoundType.DEEPSLATE_BRICKS)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
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

    @NotNull
    @Override
    public Block getCleanVariant() {
        return ModBlocks.BRICK_CHIMNEY.get();
    }

    @NotNull
    @Override
    public Block getDirtyVariant() {
        return ModBlocks.DIRTY_BRICK_CHIMNEY.get();
    }

    @Override
    public float getScrapingDropChance() {
        return 0.75f;
    }
}
