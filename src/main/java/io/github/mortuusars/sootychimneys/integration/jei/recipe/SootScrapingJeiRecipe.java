package io.github.mortuusars.sootychimneys.integration.jei.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SootScrapingJeiRecipe {

    private final Item dirtyChimney;
    private final Item cleanChimney;

    private final ItemStack dirtyChimneyStack;
    private final ItemStack cleanChimneyStack;

    public SootScrapingJeiRecipe(Item dirtyChimney, Item cleanChimney) {

        this.dirtyChimney = dirtyChimney;
        this.cleanChimney = cleanChimney;
        this.dirtyChimneyStack = new ItemStack(dirtyChimney);
        this.cleanChimneyStack = new ItemStack(cleanChimney);
    }

    public ItemStack getIngredientChimney() {
        return dirtyChimneyStack;
    }

    public ItemStack getResultChimney() {
        return cleanChimneyStack;
    }

    public List<ItemStack> getTools() {
        return ForgeRegistries.ITEMS.getValues().stream()
                .map(ItemStack::new)
                .filter(stack -> stack.canPerformAction(ToolActions.AXE_SCRAPE))
                .toList();
    }
}
