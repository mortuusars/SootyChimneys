package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.blocks.*;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks {
    public static final ArrayList<RegistryObject<? extends ChimneyBlock>> CHIMNEYS = new ArrayList<>();

    public static final RegistryObject<BrickChimneyBlock> BRICK_CHIMNEY =
            registerChimneyBlock("brick_chimney", BrickChimneyBlock::new);
    public static final RegistryObject<BrickChimneyBlock> DIRTY_BRICK_CHIMNEY =
            registerChimneyBlock("dirty_brick_chimney", BrickChimneyBlock::new);

    public static final RegistryObject<StoneBrickChimneyBlock> STONE_BRICK_CHIMNEY =
            registerChimneyBlock("stone_brick_chimney", StoneBrickChimneyBlock::new);
    public static final RegistryObject<StoneBrickChimneyBlock> DIRTY_STONE_BRICK_CHIMNEY =
            registerChimneyBlock("dirty_stone_brick_chimney", StoneBrickChimneyBlock::new);

    public static final RegistryObject<TerracottaChimneyBlock> TERRACOTTA_CHIMNEY =
            registerChimneyBlock("terracotta_chimney", TerracottaChimneyBlock::new);
    public static final RegistryObject<TerracottaChimneyBlock> DIRTY_TERRACOTTA_CHIMNEY =
            registerChimneyBlock("dirty_terracotta_chimney", TerracottaChimneyBlock::new);

    public static final RegistryObject<CopperChimneyBlock> COPPER_CHIMNEY =
            registerChimneyBlock("copper_chimney", CopperChimneyBlock::new);
    public static final RegistryObject<CopperChimneyBlock> DIRTY_COPPER_CHIMNEY =
            registerChimneyBlock("dirty_copper_chimney", CopperChimneyBlock::new);

    public static void init(){}

    private static <T extends ChimneyBlock> RegistryObject<T> registerChimneyBlock(String registryName, Supplier<T> blockSupplier){
        RegistryObject<T> block = Registry.BLOCKS.register(registryName, blockSupplier);
        CHIMNEYS.add(block);
        return block;
    }
}
