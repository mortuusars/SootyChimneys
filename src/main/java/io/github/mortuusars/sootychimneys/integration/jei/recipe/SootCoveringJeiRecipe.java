package io.github.mortuusars.sootychimneys.integration.jei.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SootCoveringJeiRecipe {
    private final Item dirtyChimney;
    private final Item cleanChimney;

    private final ItemStack dirtyChimneyStack;
    private final ItemStack cleanChimneyStack;

    public SootCoveringJeiRecipe(Item dirtyChimney, Item cleanChimney) {

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
}
