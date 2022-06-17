package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.BRICK_CHIMNEY.get())
                .pattern("b b")
                .pattern("B B")
                .pattern("BCB")
                .define('b', Items.BRICK)
                .define('B', Items.BRICKS)
                .define('C', Items.COAL)
                .group("brick_chimney")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(pFinishedRecipeConsumer, "brick_chimney_coal");

        ShapedRecipeBuilder.shaped(ModBlocks.BRICK_CHIMNEY.get())
                .pattern("b b")
                .pattern("B B")
                .pattern("BCB")
                .define('b', Items.BRICK)
                .define('B', Items.BRICKS)
                .define('C', Items.CHARCOAL)
                .group("brick_chimney")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(pFinishedRecipeConsumer, "brick_chimney_charcoal");
    }
}
