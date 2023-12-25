package io.github.mortuusars.sootychimneys.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.mortuusars.sootychimneys.core.ISootyChimney;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
import io.github.mortuusars.sootychimneys.setup.ModRecipeSerializers;
import io.github.mortuusars.sootychimneys.setup.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SootScrapingRecipe implements Recipe<Container> {
    public static final int MAX_RESULTS = 3;

    private final ResourceLocation id;
    private final Ingredient chimney;
    private final Ingredient tool;
    private final NonNullList<ChanceResult> results;

    public SootScrapingRecipe(ResourceLocation id, Ingredient chimney, Ingredient tool, NonNullList<ChanceResult> results) {
        this.id = id;
        this.chimney = chimney;
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

    public Ingredient getIngredientChimney() {
        return chimney;
    }

    public ItemStack getResultChimney() {
        for (ItemStack item : chimney.getItems()) {
            if (item.getItem() instanceof BlockItem blockItem
                    && blockItem.getBlock() instanceof ISootyChimney sootyChimney)
                return new ItemStack(sootyChimney.getCleanVariant().asItem());
        }

        return new ItemStack(Items.BARRIER);
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.chimney);
        return nonnulllist;
    }

    public Ingredient getTool() {
        return this.tool;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return this.results.get(0).getStack();
    }

    public List<ChanceResult> getResults() {
        return results;
    }

    @Override
    public boolean matches(Container container, @NotNull Level level) {
        if (container.isEmpty())
            return false;

        int itemsInContainer = 0;
        boolean chimneyMatched = false;
        boolean toolMatched = false;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty())
                itemsInContainer++;

            if (chimney.test(stack))
                chimneyMatched = true;
            else if (tool.test(stack))
                toolMatched = true;
        }

        return itemsInContainer <= 2 && chimneyMatched && toolMatched;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container container) {
        return getResultChimney();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    protected int getMaxInputCount() {
        return 2;
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
            final Ingredient chimney = Ingredient.fromJson(json.getAsJsonObject("chimney"));
            final Ingredient tool = Ingredient.fromJson(json.getAsJsonObject("tool"));

            if (chimney.isEmpty()) {
                throw new JsonParseException("No 'chimney' is set for soot scraping recipe");
            } else if (tool.isEmpty()) {
                throw new JsonParseException("No 'tool' is set for soot scraping recipe");
            } else {
                final NonNullList<ChanceResult> results = NonNullList.create();
                JsonArray resultsJsonArray = GsonHelper.getAsJsonArray(json, "results");
                for (JsonElement result : resultsJsonArray) {
                    results.add(ChanceResult.fromJson(result));
                }
                if (results.size() > MAX_RESULTS) {
                    throw new JsonParseException("Too many results for soot scraping recipe! The maximum quantity of unique results is " + MAX_RESULTS);
                } else {
                    return new SootScrapingRecipe(recipeId, chimney, tool, results);
                }
            }
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, SootScrapingRecipe recipe) {
            recipe.chimney.toNetwork(buffer);
            recipe.tool.toNetwork(buffer);
            buffer.writeVarInt(recipe.results.size());
            for (ChanceResult result : recipe.results) {
                result.toBuffer(buffer);
            }
        }

        @Nullable
        @Override
        public SootScrapingRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            Ingredient chimney = Ingredient.fromNetwork(buffer);
            Ingredient tool = Ingredient.fromNetwork(buffer);

            int resultsCount = buffer.readVarInt();
            NonNullList<ChanceResult> results = NonNullList.withSize(resultsCount, ChanceResult.EMPTY);
            results.replaceAll(ignored -> ChanceResult.fromBuffer(buffer));

            return new SootScrapingRecipe(recipeId, chimney, tool, results);
        }
    }
}
