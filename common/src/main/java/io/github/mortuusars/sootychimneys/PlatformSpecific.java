package io.github.mortuusars.sootychimneys;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.ItemStack;

public class PlatformSpecific {
    @ExpectPlatform
    public static boolean canBeUsedToScrapeSoot(ItemStack stack) {
        throw new AssertionError();
    }
}
