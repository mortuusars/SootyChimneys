package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CompletableFuture.supplyAsync(VanillaRegistries::createLookup), SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        ModBlocks.CHIMNEYS.forEach((chimney) -> {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chimney.get());
            tag(ModTags.Blocks.CHIMNEYS).add(chimney .get());
        });
    }
}
