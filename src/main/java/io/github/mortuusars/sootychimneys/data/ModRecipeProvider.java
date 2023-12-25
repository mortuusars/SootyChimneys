package io.github.mortuusars.sootychimneys.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.data.builder.SootScrapingRecipeBuilder;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ToolActionIngredient;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder.shaped(ModItems.BRICK_CHIMNEY.get())
                .pattern("b b")
                .pattern("B B")
                .pattern("BCB")
                .define('b', Items.BRICK)
                .define('B', Items.BRICKS)
                .define('C', ItemTags.COALS)
                .group("brick_chimney")
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(recipeConsumer, "brick_chimney");

        ShapedRecipeBuilder.shaped(ModItems.STONE_BRICK_CHIMNEY.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("BCB")
                .define('B', Items.STONE_BRICKS)
                .define('C', ItemTags.COALS)
                .group("stone_brick_chimney")
                .unlockedBy("has_stone", has(Items.STONE))
                .save(recipeConsumer, "stone_brick_chimney");

        ShapedRecipeBuilder.shaped(ModItems.TERRACOTTA_CHIMNEY.get())
                .pattern("   ")
                .pattern("T T")
                .pattern("TCT")
                .define('T', Items.TERRACOTTA)
                .define('C', ItemTags.COALS)
                .group("terracotta_chimney")
                .unlockedBy("has_terracotta", has(Items.TERRACOTTA))
                .save(recipeConsumer, "terracotta_chimney");

        ShapedRecipeBuilder.shaped(ModItems.COPPER_CHIMNEY.get())
                .pattern("   ")
                .pattern("I I")
                .pattern("BCB")
                .define('B', Items.COPPER_BLOCK)
                .define('I', Items.COPPER_INGOT)
                .define('C', ItemTags.COALS)
                .group("copper_chimney")
                .unlockedBy("has_copper", has(Items.COPPER_INGOT))
                .save(recipeConsumer, "copper_chimney");

        // Soot Scraping
        ModItems.CHIMNEYS.stream()
                .map(RegistryObject::get)
                .filter(blockItem -> blockItem.getBlock() instanceof ISootyChimney chimney && chimney.isDirty())
                .forEach(chimney -> {
                    ISootyChimney chimneyBlock = (ISootyChimney) chimney.getBlock();
                    new SootScrapingRecipeBuilder(Ingredient.of(chimney),
                            new ToolActionIngredient(ToolActions.AXE_SCRAPE), Items.BLACK_DYE, 1, chimneyBlock.getScrapingDropChance())
                            .build(recipeConsumer);
                });
    }
}
