package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Objects;

public class ModLootTablesProvider extends BaseLootTableProvider {

    public ModLootTablesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void addTables() {
        ModBlocks.CHIMNEYS.forEach(
                chimney -> {
                    String path = Objects.requireNonNull(chimney.getId()).getPath();
                    Block chimneyBlock = chimney.get();

                    blockLootTables.put(chimneyBlock, createSimpleTable(path, chimneyBlock));

                    // Soot Scraping:
                    if (chimneyBlock instanceof ISootyChimney sootyChimney && sootyChimney.isDirty())
                        customLootTables.put(
                                new ResourceLocation(SootyChimneys.MOD_ID + ":soot_scraping/" + path),
                                createSootLootTable(Items.BLACK_DYE, ConstantValue.exactly(1), sootyChimney.getScrapingDropChance()));
                }
        );
    }

    @SuppressWarnings("SameParameterValue")
    private LootTable createSootLootTable(ItemLike itemLike, NumberProvider rolls, float chance){
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(rolls)
                .add(LootItem.lootTableItem(itemLike))
                .when(LootItemRandomChanceCondition.randomChance(chance));

        return LootTable.lootTable()
                .withPool(pool)
                .build();
    }
}
