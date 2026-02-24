package io.github.mortuusars.sootychimneys.forge;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolActions;

public class PlatformSpecificImpl {
    public static boolean canBeUsedToScrapeSoot(ItemStack stack) {
        return stack.canPerformAction(ToolActions.AXE_SCRAPE);
    }
}
