package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ModItems {
    public static void init() {
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            Registry.ITEMS.register(chimney.getCleanId(), () -> new BlockItem(chimney.getCleanBlock(), new Item.Properties()));
            Registry.ITEMS.register(chimney.getDirtyId(), () -> new BlockItem(chimney.getDirtyBlock(), new Item.Properties()));
        }
    }
}
