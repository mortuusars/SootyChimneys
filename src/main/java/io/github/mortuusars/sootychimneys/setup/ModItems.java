package io.github.mortuusars.sootychimneys.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final RegistryObject<BlockItem> BRICK_CHIMNEY = Registry.ITEMS.register("brick_chimney",
            () -> new BlockItem(ModBlocks.BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<BlockItem> DIRTY_BRICK_CHIMNEY = Registry.ITEMS.register("dirty_brick_chimney",
            () -> new BlockItem(ModBlocks.DIRTY_BRICK_CHIMNEY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static void init() { }
}
