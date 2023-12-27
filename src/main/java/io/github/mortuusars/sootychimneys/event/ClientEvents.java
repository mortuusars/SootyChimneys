package io.github.mortuusars.sootychimneys.event;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SootyChimneys.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onCreativeTabsBuild(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
                event.accept(chimney.getCleanItem());
                event.accept(chimney.getDirtyItem());
            }
        }
    }
}
