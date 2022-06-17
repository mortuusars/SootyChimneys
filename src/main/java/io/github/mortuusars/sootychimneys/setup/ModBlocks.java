package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.blocks.BrickChimneyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final RegistryObject<BrickChimneyBlock> BRICK_CHIMNEY = Registry.BLOCKS.register("brick_chimney",
            () -> new BrickChimneyBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<BrickChimneyBlock> DIRTY_BRICK_CHIMNEY = Registry.BLOCKS.register("dirty_brick_chimney",
            () -> new BrickChimneyBlock(BlockBehaviour.Properties.of(Material.STONE)));

    public static void init(){}
}
