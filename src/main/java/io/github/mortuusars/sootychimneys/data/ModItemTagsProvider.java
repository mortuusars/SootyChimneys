package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator pGenerator, ModBlockTagsProvider pBlockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(pGenerator.getPackOutput(), CompletableFuture.supplyAsync(VanillaRegistries::createLookup),
                pBlockTagsProvider.contentsGetter(), SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        ModItems.CHIMNEYS.forEach((chimneyItem) ->
                tag(ModTags.Items.CHIMNEYS).add(chimneyItem.get()));
    }
}
