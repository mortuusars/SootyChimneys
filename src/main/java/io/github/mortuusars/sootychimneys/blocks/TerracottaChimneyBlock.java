package io.github.mortuusars.sootychimneys.blocks;

import com.mojang.math.Vector3f;
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
import net.minecraftforge.common.data.SoundDefinition;
import org.jetbrains.annotations.NotNull;

public class TerracottaChimneyBlock extends ChimneyBlock implements ISootyChimney{

    private static final VoxelShape _shape = Block.box(5,0,5, 11,8,11);
    private static final Vector3f _particleOriginOffset = new Vector3f(0.5f, 0.75f, 0.5f);
    private static final Vector3f _particleMaxRandomOffset = new Vector3f(0.02f, 0.05f, 0.02f);

    public TerracottaChimneyBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).sound(SoundType.DRIPSTONE_BLOCK));
    }

    @Override
    public float getSmokeIntensity() {
        return 0.2f;
    }

    @Override
    public float getSmokeSpeed() {
        return 0.65f;
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
