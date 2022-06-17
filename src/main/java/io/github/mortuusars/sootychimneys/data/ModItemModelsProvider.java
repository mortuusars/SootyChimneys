package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(ModItems.BRICK_CHIMNEY.get().getRegistryName().getPath(), modLoc("block/brick_chimney"));
        withExistingParent(ModItems.DIRTY_BRICK_CHIMNEY.get().getRegistryName().getPath(), modLoc("block/dirty_brick_chimney"));
    }
}
