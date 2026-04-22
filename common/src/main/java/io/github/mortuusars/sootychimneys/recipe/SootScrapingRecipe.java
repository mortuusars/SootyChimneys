package io.github.mortuusars.sootychimneys.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.data.Chimney;
import io.github.mortuusars.sootychimneys.recipe.result.ChanceResult;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SootScrapingRecipe implements Recipe<SingleRecipeInput> {
    public static final int MAX_RESULTS = 6;

    public static final MapCodec<SootScrapingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC
                      .fieldOf("chimney")
                      .forGetter(SootScrapingRecipe::chimney),
                ChanceResult.CHANCE_RESULT_ONLY_CODEC.listOf(0, MAX_RESULTS)
                      .fieldOf("results")
                      .forGetter(SootScrapingRecipe::results))
          .apply(instance, SootScrapingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SootScrapingRecipe> STREAM_CODEC =
          StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, SootScrapingRecipe::chimney,
                ChanceResult.STREAM_CODEC.apply(ByteBufCodecs.list(MAX_RESULTS)), SootScrapingRecipe::results,
                SootScrapingRecipe::new
          );

    private final Ingredient chimney;
    private final List<ChanceResult> results;
    private @Nullable PlacementInfo placementInfo;

    public SootScrapingRecipe(Ingredient chimney, List<ChanceResult> results) {
        this.chimney = chimney;
        this.results = results;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public @NonNull String group() {
        return "";
    }

    public ItemStack getResultChimney() {
        //noinspection deprecation
        Item chimneyItem = chimney.items().findFirst().map(Holder::value).orElse(Items.BARRIER);

        if (chimneyItem instanceof BlockItem blockItem
              && blockItem.getBlock() instanceof ChimneyBlock chimneyBlock
              && chimneyBlock.isDirty()) {
            Item cleanItem = Chimney.getCleanBlock(chimneyBlock).asItem();
            return new ItemStack(cleanItem);
        }

        return new ItemStack(Items.BARRIER);
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        if (placementInfo == null) {
            placementInfo = PlacementInfo.create(this.chimney);
        }
        return placementInfo;
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return chimney().test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input) {
        return getResultChimney();
    }

    @Override
    public @NotNull RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return SootyChimneys.RecipeSerializers.SOOT_SCRAPING.get();
    }

    @Override
    public @NotNull RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return SootyChimneys.RecipeTypes.SOOT_SCRAPING.get();
    }

    public Ingredient chimney() {
        return chimney;
    }

    public List<ChanceResult> results() {
        return results;
    }
}
