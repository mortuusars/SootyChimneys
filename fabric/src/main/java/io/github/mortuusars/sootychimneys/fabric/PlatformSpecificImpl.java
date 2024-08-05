package io.github.mortuusars.sootychimneys.fabric;

import net.minecraft.world.item.ItemStack;

public class PlatformSpecificImpl {
    public static boolean canBeUsedToScrapeSoot(ItemStack stack) {
        return stack.is(SootyChimneysFabric.Tags.Items.SOOT_SCRAPERS);
    }
}
