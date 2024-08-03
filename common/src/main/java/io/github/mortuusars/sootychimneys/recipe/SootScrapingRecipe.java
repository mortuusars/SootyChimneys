package io.github.mortuusars.sootychimneys.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.data.Chimney;
import io.github.mortuusars.sootychimneys.recipe.result.ChanceResult;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
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

import java.util.List;

public record SootScrapingRecipe(Ingredient chimney, List<ChanceResult> results) implements Recipe<SingleRecipeInput> {
    public static final int MAX_RESULTS = 6;

    @Override
    public boolean isSpecial() {
        return true;
    }

    public ItemStack getResultChimney() {
        for (ItemStack item : chimney.getItems()) {
            if (item.getItem() instanceof BlockItem blockItem
                    && blockItem.getBlock() instanceof ChimneyBlock chimneyBlock
                    && chimneyBlock.isDirty()) {
                Item cleanItem = Chimney.getCleanBlock(chimneyBlock).asItem();
                return new ItemStack(cleanItem);
            }
        }

        return new ItemStack(Items.BARRIER);
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.chimney);
        return nonnulllist;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return chimney().test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return getResultChimney();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.results.getFirst().stack();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SootyChimneys.RecipeSerializers.SOOT_SCRAPING.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SootyChimneys.RecipeTypes.SOOT_SCRAPING.get();
    }

    public static class Serializer implements RecipeSerializer<SootScrapingRecipe> {
        public static final MapCodec<SootScrapingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Ingredient.CODEC
                                .fieldOf("chimney")
                                .forGetter(SootScrapingRecipe::chimney),
                        ChanceResult.CODEC.listOf(0, 3)
                                .validate(list -> list.size() <= MAX_RESULTS
                                        ? DataResult.success(list)
                                        : DataResult.error(() -> "SootScrapingRecipe should have at most " + MAX_RESULTS + " results."))
                                .fieldOf("results")
                                .forGetter(SootScrapingRecipe::results))
                .apply(instance, SootScrapingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SootScrapingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, SootScrapingRecipe::chimney,
                        ChanceResult.STREAM_CODEC.apply(ByteBufCodecs.list(MAX_RESULTS)), SootScrapingRecipe::results,
                        SootScrapingRecipe::new
                );

        @Override
        public @NotNull MapCodec<SootScrapingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SootScrapingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
