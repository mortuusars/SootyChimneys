package io.github.mortuusars.sootychimneys.data.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
import io.github.mortuusars.sootychimneys.setup.ModRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SootScrapingRecipeBuilder {
    private final List<ChanceResult> results = new ArrayList<>(4);
    private final Ingredient input;
    private final Ingredient tool;

    public SootScrapingRecipeBuilder(Ingredient input, Ingredient tool, ItemLike mainResult, int count, float chance) {
        this.input = input;
        this.tool = tool;
        this.results.add(new ChanceResult(new ItemStack(mainResult.asItem(), count), chance));
    }

    public SootScrapingRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1);
    }

    public SootScrapingRecipeBuilder addResult(ItemLike result, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), 1));
        return this;
    }

    public SootScrapingRecipeBuilder addResultWithChance(ItemLike result, float chance) {
        return this.addResultWithChance(result, chance, 1);
    }

    public SootScrapingRecipeBuilder addResultWithChance(ItemLike result, float chance, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), chance));
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.input.getItems()[0].getItem());
        if (location.toString().equals("minecraft:barrier"))
            throw new IllegalStateException("Cannot get path from the ingredient: '" + input.toJson() + "'. Provide custom save path.");
        this.build(consumerIn, location.getPath());
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.input.getItems()[0].getItem());
        ResourceLocation saveLocation = new ResourceLocation(SootyChimneys.MOD_ID, "soot_scraping/" + save);
        if (saveLocation.equals(resourcelocation)) {
            throw new IllegalStateException("Soot Scraping Recipe '" + save + "' should remove its 'save' argument as it's the same as the default.");
        } else {
            this.build(consumerIn, saveLocation);
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        // Sort outputs with larger chance first:
        results.sort((result1, result2) -> result1.getChance() > result2.getChance() ? -1 : 1);
        consumerIn.accept(new SootScrapingRecipeBuilder.Result(id, this.input, this.tool, this.results));
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Ingredient tool;
        private final List<ChanceResult> results;

        public Result(ResourceLocation id, Ingredient ingredient, Ingredient tool, List<ChanceResult> results) {
            this.id = id;
            this.ingredient = ingredient;
            this.tool = tool;
            this.results = results;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredient", this.ingredient.toJson());
            json.add("tool", this.tool.toJson());

            JsonArray arrayResults = new JsonArray();
            for (ChanceResult result : this.results) {
                arrayResults.add(result.toJson());
            }
            json.add("results", arrayResults);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.SOOT_SCRAPING.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
