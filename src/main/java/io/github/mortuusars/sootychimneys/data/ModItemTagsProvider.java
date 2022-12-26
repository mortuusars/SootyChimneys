package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.setup.ModItems;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.VanillaItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends VanillaItemTagsProvider {
    public ModItemTagsProvider(DataGenerator pGenerator, ModBlockTagsProvider pBlockTagsProvider) {
        super(pGenerator.getPackOutput(), CompletableFuture.supplyAsync(VanillaRegistries::createLookup), pBlockTagsProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        ModItems.CHIMNEYS.forEach((chimneyItem) ->
                tag(ModTags.Items.CHIMNEY).add(chimneyItem.get()));
    }
}
