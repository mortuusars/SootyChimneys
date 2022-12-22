package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ModBlocks.CHIMNEYS.forEach((chimney) -> {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chimney.get());
            tag(ModTags.Blocks.CHIMNEY).add(chimney.get());
        });
    }
}
