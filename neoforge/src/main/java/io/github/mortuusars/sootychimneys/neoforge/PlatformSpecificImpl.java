package io.github.mortuusars.sootychimneys.neoforge;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.ItemAbilities;

public class PlatformSpecificImpl {
    public static boolean canBeUsedToScrapeSoot(ItemStack stack) {
        return stack.canPerformAction(ItemAbilities.AXE_SCRAPE);
    }
}
