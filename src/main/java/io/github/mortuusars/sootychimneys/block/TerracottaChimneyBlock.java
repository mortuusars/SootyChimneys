package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class TerracottaChimneyBlock extends ChimneyBlock implements ISootyChimney {
    private static final VoxelShape _shape = Block.box(5,0,5, 11,8,11);

    public TerracottaChimneyBlock(ChimneySmokeProperties smokeProperties, BlockBehaviour.Properties properties) {
        super(smokeProperties, properties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return _shape;
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
