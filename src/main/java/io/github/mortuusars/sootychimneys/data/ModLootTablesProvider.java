package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.setup.ModBlockEntities;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;

public class ModLootTablesProvider extends BaseLootTableProvider {

    public ModLootTablesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void addTables() {
        ModBlocks.CHIMNEYS.forEach(
                chimney -> lootTables.put(chimney.get(), createStandardTable(chimney.get().getRegistryName().getPath(), chimney.get(), ModBlockEntities.CHIMNEY_BLOCK_ENTITY.get())
        ));
    }
}
