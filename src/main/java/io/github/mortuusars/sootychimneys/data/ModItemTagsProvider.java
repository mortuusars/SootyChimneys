package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            tag(ModTags.Items.CHIMNEYS).add(chimney.getCleanItem()).add(chimney.getDirtyItem());
        }
    }
}
