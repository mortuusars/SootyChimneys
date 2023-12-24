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
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerracottaChimneyBlock extends ChimneyBlock implements ISootyChimney {
    private static final VoxelShape SHAPE = Block.box(5,0,5, 11,8,11);
    private static final VoxelShape STACKED_SHAPE = Block.box(5,0,5, 11,16,11);

    public TerracottaChimneyBlock() {
        super(new ChimneySmokeProperties(0.5f, 0.75f, 0.5f, 0.02f, 0.05f, 0.02f)
                .setIntensity(0.2f)
                .setSpeed(0.65f),
            BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                    .sound(SoundType.DRIPSTONE_BLOCK)
                    .strength(2f, 2f)
                    .destroyTime(0.6f)
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
        return ModBlocks.TERRACOTTA_CHIMNEY.get();
    }

    @Override
    public Block getDirtyVariant() {
        return ModBlocks.DIRTY_TERRACOTTA_CHIMNEY.get();
    }

    @Override
    public float getScrapingDropChance() {
        return 0.4f;
    }
}
