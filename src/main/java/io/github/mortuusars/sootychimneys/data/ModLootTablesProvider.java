package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.data.DataGenerator;

public class ModLootTablesProvider extends BaseLootTableProvider {

    public ModLootTablesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registry.BRICK_CHIMNEY.get(), createStandardTable("brick_chimney", Registry.BRICK_CHIMNEY.get(), Registry.CHIMNEY_BLOCK_ENTITY.get()));
    }
}
