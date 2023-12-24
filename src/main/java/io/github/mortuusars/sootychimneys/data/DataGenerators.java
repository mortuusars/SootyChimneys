package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SootyChimneys.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();

        // Server
        ModBlockTagsProvider blockTagsGenerator = new ModBlockTagsProvider(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTagsGenerator);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(generator, blockTagsGenerator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ModLootTablesProvider(generator));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));

        // Client
        generator.addProvider(event.includeClient(), new ModItemModelsProvider(generator, event.getExistingFileHelper()));
    }
}
