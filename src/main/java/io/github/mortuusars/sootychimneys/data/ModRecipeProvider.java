package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.data.builder.SootScrapingRecipeBuilder;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ToolActionIngredient;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.BRICK.getCleanItem())
                .pattern("b b")
                .pattern("B B")
                .pattern("BCB")
                .define('b', Items.BRICK)
                .define('B', Items.BRICKS)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.BRICK.getCleanId())
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(recipeConsumer, SootyChimneys.Chimney.BRICK.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.COBBLESTONE.getCleanItem())
                .pattern("b b")
                .pattern("B B")
                .pattern("BCB")
                .define('b', Items.COBBLESTONE_SLAB)
                .define('B', Items.COBBLESTONE)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.COBBLESTONE.getCleanId())
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeConsumer, SootyChimneys.Chimney.COBBLESTONE.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.STONE_BRICK.getCleanItem())
                .pattern("B B")
                .pattern("B B")
                .pattern("BCB")
                .define('B', Items.STONE_BRICKS)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.STONE_BRICK.getCleanId())
                .unlockedBy("has_stone", has(Items.STONE))
                .save(recipeConsumer, SootyChimneys.Chimney.STONE_BRICK.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.MUD_BRICK.getCleanItem())
                .pattern("B B")
                .pattern("B B")
                .pattern("BCB")
                .define('B', Items.MUD_BRICKS)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.MUD_BRICK.getCleanId())
                .unlockedBy("has_mud", has(Items.MUD))
                .save(recipeConsumer, SootyChimneys.Chimney.MUD_BRICK.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.IRON.getCleanItem())
                .pattern("I I")
                .pattern("N N")
                .pattern("ICI")
                .define('N', Tags.Items.NUGGETS_IRON)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.IRON.getCleanId())
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeConsumer, SootyChimneys.Chimney.IRON.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.COPPER.getCleanItem())
                .pattern("   ")
                .pattern("I I")
                .pattern("BCB")
                .define('B', Tags.Items.STORAGE_BLOCKS_COPPER)
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.COPPER.getCleanId())
                .unlockedBy("has_copper", has(Items.COPPER_INGOT))
                .save(recipeConsumer, SootyChimneys.Chimney.COPPER.getCleanId());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SootyChimneys.Chimney.TERRACOTTA.getCleanItem())
                .pattern("   ")
                .pattern("T T")
                .pattern("TCT")
                .define('T', Items.TERRACOTTA)
                .define('C', ItemTags.COALS)
                .group(SootyChimneys.Chimney.TERRACOTTA.getCleanId())
                .unlockedBy("has_terracotta", has(Items.TERRACOTTA))
                .save(recipeConsumer, SootyChimneys.Chimney.TERRACOTTA.getCleanId());


        // Soot Scraping
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            new SootScrapingRecipeBuilder(Ingredient.of(chimney.getDirtyItem()),
                    new ToolActionIngredient(ToolActions.AXE_SCRAPE), Items.BLACK_DYE, 1, chimney.getDefaultScrapeChance())
                    .build(recipeConsumer);
        }
    }
}
