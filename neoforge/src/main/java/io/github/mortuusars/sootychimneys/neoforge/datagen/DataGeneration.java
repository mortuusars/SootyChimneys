package io.github.mortuusars.sootychimneys.neoforge.datagen;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.neoforge.datagen.server.RecipesDatagen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = SootyChimneys.ID)
public class DataGeneration {
    @SubscribeEvent
    public static void gatherDataServer(GatherDataEvent.Server event) {
        event.createProvider(RecipesDatagen.Runner::new);
    }
}
