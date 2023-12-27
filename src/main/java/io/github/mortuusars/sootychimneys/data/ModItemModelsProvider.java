package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            withExistingParent(chimney.getCleanId(), modLoc("block/" + chimney.getCleanId()));
            withExistingParent(chimney.getDirtyId(), modLoc("block/" + chimney.getDirtyId()));
        }
    }
}
