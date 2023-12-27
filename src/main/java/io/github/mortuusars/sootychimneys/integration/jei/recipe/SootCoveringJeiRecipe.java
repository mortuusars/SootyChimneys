package io.github.mortuusars.sootychimneys.integration.jei.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SootCoveringJeiRecipe {
    private final Item cleanChimney;
    private final Item dirtyChimney;

    private final ItemStack cleanChimneyStack;
    private final ItemStack dirtyChimneyStack;

    public SootCoveringJeiRecipe(Item cleanChimney, Item dirtyChimney) {
        this.cleanChimney = cleanChimney;
        this.dirtyChimney = dirtyChimney;
        this.cleanChimneyStack = new ItemStack(cleanChimney);
        this.dirtyChimneyStack = new ItemStack(dirtyChimney);
    }

    public ItemStack getCleanChimney() {
        return cleanChimneyStack;
    }

    public ItemStack getDirtyChimney() {
        return dirtyChimneyStack;
    }
}
