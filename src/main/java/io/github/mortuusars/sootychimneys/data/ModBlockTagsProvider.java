package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends VanillaBlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output) {
        super(output, CompletableFuture.supplyAsync(VanillaRegistries::createLookup));
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        ModBlocks.CHIMNEYS.forEach((chimney) -> {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(chimney.get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(chimney.get());
            tag(ModTags.Blocks.CHIMNEY).add(chimney
                    .get());
        });
    }
}
