package io.github.mortuusars.sootychimneys.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
import io.github.mortuusars.sootychimneys.setup.ModRecipeSerializers;
import io.github.mortuusars.sootychimneys.setup.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SootScrapingRecipe implements Recipe<RecipeWrapper> {

    public static final int MAX_RESULTS = 4;

    private final ResourceLocation id;
    private final String group;
    private final Ingredient input;
    private final Ingredient tool;
    private final NonNullList<ChanceResult> results;

    public SootScrapingRecipe(ResourceLocation id, String group, Ingredient input, Ingredient tool, NonNullList<ChanceResult> results) {
        this.id = id;
        this.group = group;
        this.input = input;
        this.tool = tool;
        this.results = results;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.input);
        return nonnulllist;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public Ingredient getTool() {
        return this.tool;
    }

    @Override
    public boolean matches(RecipeWrapper container, @NotNull Level pLevel) {
        if (container.isEmpty())
            return false;
        return input.test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(RecipeWrapper pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return this.results.get(0).getStack().copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess pRegistryAccess) {
        return this.results.get(0).getStack();
    }

    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.SOOT_SCRAPING.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipeTypes.SOOT_SCRAPING.get();
    }

    public static class Serializer implements RecipeSerializer<SootScrapingRecipe> {
        @Override
        public @NotNull SootScrapingRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            final String group = GsonHelper.getAsString(json, "group", "");

            final Ingredient ingredient = Ingredient.fromJson(json.getAsJsonObject("ingredient"));
            final JsonObject toolObject = GsonHelper.getAsJsonObject(json, "tool");
            final Ingredient tool = Ingredient.fromJson(toolObject);
            if (ingredient.isEmpty()) {
                throw new JsonParseException("No ingredients for soot scraping recipe");
            } else if (tool.isEmpty()) {
                throw new JsonParseException("No tool for soot scraping recipe");
            } else {
                final NonNullList<ChanceResult> results = NonNullList.create();
                JsonArray resultsJsonArray = GsonHelper.getAsJsonArray(json, "results");
                for (JsonElement result : resultsJsonArray) {
                    results.add(ChanceResult.fromJson(result));
                }
                if (results.size() > MAX_RESULTS) {
                    throw new JsonParseException("Too many results for soot scraping recipe! The maximum quantity of unique results is 4");
                } else {
                    return new SootScrapingRecipe(recipeId, group, ingredient, tool, results);
                }
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SootScrapingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.input.toNetwork(buffer);
            recipe.tool.toNetwork(buffer);
            buffer.writeVarInt(recipe.results.size());
            for (ChanceResult result : recipe.results) {
                result.toBuffer(buffer);
            }
        }

        @SuppressWarnings("Java8ListReplaceAll")
        @Nullable
        @Override
        public SootScrapingRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            Ingredient input = Ingredient.fromNetwork(buffer);
            Ingredient tool = Ingredient.fromNetwork(buffer);

            int resultsCount = buffer.readVarInt();
            NonNullList<ChanceResult> results = NonNullList.withSize(resultsCount, ChanceResult.EMPTY);
            for (int i = 0; i < results.size(); ++i) {
                results.set(i, ChanceResult.fromBuffer(buffer));
            }

            return new SootScrapingRecipe(recipeId, group, input, tool, results);
        }
    }
}
