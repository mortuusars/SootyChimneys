package io.github.mortuusars.sootychimneys.neoforge.datagen.server;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipesDatagen extends RecipeProvider {
    public RecipesDatagen(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.BRICK_CHIMNEY.get())
              .define('B', Blocks.BRICKS)
              .define('C', ItemTags.COALS)
              .define('b', Items.BRICK)
              .pattern("b b")
              .pattern("B B")
              .pattern("BCB")
              .unlockedBy("has_brick", has(Items.BRICK))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.COBBLESTONE_CHIMNEY.get())
              .define('B', Blocks.COBBLESTONE)
              .define('C', ItemTags.COALS)
              .define('b', Blocks.COBBLESTONE_SLAB)
              .pattern("b b")
              .pattern("B B")
              .pattern("BCB")
              .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.STONE_BRICK_CHIMNEY.get())
              .define('B', Blocks.STONE_BRICKS)
              .define('C', ItemTags.COALS)
              .pattern("B B")
              .pattern("B B")
              .pattern("BCB")
              .unlockedBy("has_stone", has(Items.STONE))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.MUD_BRICK_CHIMNEY.get())
              .define('B', Blocks.MUD_BRICKS)
              .define('C', ItemTags.COALS)
              .pattern("B B")
              .pattern("B B")
              .pattern("BCB")
              .unlockedBy("has_mud", has(Items.MUD))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.IRON_CHIMNEY.get())
              .define('I', Tags.Items.INGOTS_IRON)
              .define('C', ItemTags.COALS)
              .define('N', Tags.Items.NUGGETS_IRON)
              .pattern("I I")
              .pattern("N N")
              .pattern("ICI")
              .unlockedBy("has_iron", has(Tags.Items.NUGGETS_IRON))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.COPPER_CHIMNEY.get())
              .define('I', Tags.Items.INGOTS_COPPER)
              .define('C', ItemTags.COALS)
              .define('B', Tags.Items.STORAGE_BLOCKS_COPPER)
              .pattern("   ")
              .pattern("I I")
              .pattern("BCB")
              .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
              .save(output);

        shaped(RecipeCategory.DECORATIONS, SootyChimneys.Items.TERRACOTTA_CHIMNEY.get())
              .define('T', Blocks.TERRACOTTA)
              .define('C', ItemTags.COALS)
              .pattern("   ")
              .pattern("T T")
              .pattern("TCT")
              .unlockedBy("has_terracotta", has(Blocks.TERRACOTTA))
              .save(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider registries, @NotNull RecipeOutput output) {
            return new RecipesDatagen(registries, output);
        }

        @Override
        public @NotNull String getName() {
            return "sooty_chimneys_recipes";
        }
    }
}
