package io.github.mortuusars.sootychimneys.loot;

import com.mojang.logging.LogUtils;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModLootTables {
    public static final ResourceLocation SOOT_SCRAPING = new ResourceLocation(SootyChimneys.MOD_ID, "soot_scraping");

    public static List<ItemStack> getSootScrapingLootFor(BlockState state, ServerLevel level) {
        if (!(state.getBlock() instanceof ISootyChimney)){
            LogUtils.getLogger().error("Soot Scraping is only for ISootyChimney blocks.");
            return Collections.emptyList();
        }

        String blockId  = Objects.requireNonNull(state.getBlock().getRegistryName()).getPath();

        try {
            LootContext lootContext = new LootContext.Builder(level)
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .create(LootContextParamSets.EMPTY);

            LootTable table = level.getServer().getLootTables().get(new ResourceLocation(SOOT_SCRAPING + "/" + blockId));
            return table.getRandomItems(lootContext);
        }
        catch (Exception ex) {
            LogUtils.getLogger().error("Failed to get 'soot_scraping' loot table for '{}'. - {}", blockId, ex.toString());
            return Collections.emptyList();
        }
    }
}
