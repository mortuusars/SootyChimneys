package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ModLootTables {

    public static class BlockLootProvider extends VanillaBlockLoot {
        @Override
        protected void generate() {
            for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
                dropSelf(chimney.getCleanBlock());
                dropSelf(chimney.getDirtyBlock());
            }
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(e -> e.getKey().location().getNamespace().equals(SootyChimneys.MOD_ID))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }
    }

    public static class SootScrapingProvider implements LootTableSubProvider {
        @Override
        public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> output) {
            for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
                output.accept(SootyChimneys.resource("soot_scraping/" + chimney.getDirtyId()), LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BLACK_DYE))
                                .when(LootItemRandomChanceCondition.randomChance(chimney.getDefaultScrapeChance()))));
            }
        }
    }
}
