package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModLootTablesProvider extends BaseLootTableProvider {

    public ModLootTablesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void addTables() {
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            ChimneyBlock cleanBlock = chimney.getCleanBlock();
            blockLootTables.put(cleanBlock, createSimpleTable(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(cleanBlock)).getPath(), cleanBlock));
            ChimneyBlock dirtyBlock = chimney.getDirtyBlock();
            String dirtyBlockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(dirtyBlock)).getPath();
            blockLootTables.put(dirtyBlock, createSimpleTable(dirtyBlockPath, dirtyBlock));

            // Soot Scraping:
            customLootTables.put(
                    new ResourceLocation(SootyChimneys.MOD_ID + ":soot_scraping/" + dirtyBlockPath),
                    createSootLootTable(Items.BLACK_DYE, ConstantValue.exactly(1), chimney.getDefaultScrapeChance()));
        }
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
