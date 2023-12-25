package io.github.mortuusars.sootychimneys.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModItems {
    public static final ArrayList<RegistryObject<? extends BlockItem>> CHIMNEYS = new ArrayList<>();

    public static final RegistryObject<BlockItem> BRICK_CHIMNEY = chimneyBlockItem("brick_chimney",
            () -> new BlockItem(ModBlocks.BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BlockItem> DIRTY_BRICK_CHIMNEY = chimneyBlockItem("dirty_brick_chimney",
            () -> new BlockItem(ModBlocks.DIRTY_BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BlockItem> STONE_BRICK_CHIMNEY = chimneyBlockItem("stone_brick_chimney",
            () -> new BlockItem(ModBlocks.STONE_BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BlockItem> DIRTY_STONE_BRICK_CHIMNEY = chimneyBlockItem("dirty_stone_brick_chimney",
            () -> new BlockItem(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BlockItem> TERRACOTTA_CHIMNEY = chimneyBlockItem("terracotta_chimney",
            () -> new BlockItem(ModBlocks.TERRACOTTA_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BlockItem> DIRTY_TERRACOTTA_CHIMNEY = chimneyBlockItem("dirty_terracotta_chimney",
            () -> new BlockItem(ModBlocks.DIRTY_TERRACOTTA_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BlockItem> COPPER_CHIMNEY = chimneyBlockItem("copper_chimney",
            () -> new BlockItem(ModBlocks.COPPER_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BlockItem> DIRTY_COPPER_CHIMNEY = chimneyBlockItem("dirty_copper_chimney",
            () -> new BlockItem(ModBlocks.DIRTY_COPPER_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static void init() { }

    private static RegistryObject<BlockItem> chimneyBlockItem(String registryName, Supplier<BlockItem> blockSupplier){
        RegistryObject<BlockItem> item = Registry.ITEMS.register(registryName, blockSupplier);
        CHIMNEYS.add(item);
        return item;
    }
}
