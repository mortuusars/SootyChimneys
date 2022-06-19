package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import io.github.mortuusars.sootychimneys.setup.Registry;
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
        ModItems.CHIMNEYS.forEach((chimneyItem) -> {
            tag(Registry.CHIMNEY_ITEM_TAG).add(chimneyItem.get());
        });
    }
}
