package io.github.mortuusars.sootychimneys.integration.jei.resource;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class SootScrapingJeiRecipe {

    private final Item dirtyChimney;
    private final Item cleanChimney;

    public SootScrapingJeiRecipe(Item dirtyChimney, Item cleanChimney) {

        this.dirtyChimney = dirtyChimney;
        this.cleanChimney = cleanChimney;
    }

    public ItemStack getIngredientChimney() {
        return new ItemStack(dirtyChimney);
    }

    public ItemStack getResultChimney() {
        return new ItemStack(cleanChimney);
    }

    public List<ItemStack> getScrapeDrops() {
        LootTable lootTable = Minecraft.getInstance().level.getServer().getLootTables()
                .get(SootyChimneys.resource("soot_scraping/" + dirtyChimney.getRegistryName().getPath()));

//        lootTable.get

        return null;
    }
}
