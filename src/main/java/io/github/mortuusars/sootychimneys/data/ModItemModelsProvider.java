package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), SootyChimneys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.CHIMNEYS.forEach((chimneyItem) -> {
            String path = Objects.requireNonNull(chimneyItem.getId()).getPath();
            withExistingParent(path, modLoc("block/" + path));
        });
    }
}
