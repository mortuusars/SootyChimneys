package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class MudBrickChimneyBlock extends ChimneyBlock implements ISootyChimney {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 8, 0, 16, 16, 16),
            Block.box(0, 0, 5, 16, 8, 11),
            Block.box(5, 0, 0, 11, 8, 16));

    private static final VoxelShape STACKED_SHAPE = Shapes.or(
            Block.box(0, 0, 5, 16, 16, 11),
            Block.box(5, 0, 0, 11, 16, 16));

    public MudBrickChimneyBlock() {
        super(new ChimneySmokeProperties(0.5f, 1f, 0.5f, 0.15f, 0.1f, 0.15f)
                        .setSpeed(1.1f),
                Properties.of(Material.STONE, MaterialColor.COLOR_BROWN)
                        .sound(SoundType.MUD_BRICKS)
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

    @NotNull
    @Override
    public Block getCleanVariant() {
        return ModBlocks.MUD_BRICK_CHIMNEY.get();
    }

    @NotNull
    @Override
    public Block getDirtyVariant() {
        return ModBlocks.DIRTY_MUD_BRICK_CHIMNEY.get();
    }

    @Override
    public float getScrapingDropChance() {
        return 0.75f;
    }
}
