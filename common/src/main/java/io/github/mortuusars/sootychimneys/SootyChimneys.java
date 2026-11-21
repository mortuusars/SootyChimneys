package io.github.mortuusars.sootychimneys;

import io.github.mortuusars.mortaar.Registrar;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.block.ChimneyBlockEntity;
import io.github.mortuusars.sootychimneys.data.Chimney;
import io.github.mortuusars.sootychimneys.data.ChimneyTypes;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class SootyChimneys {
    public static final String ID = "sootychimneys";

    public static final Registrar REGISTRAR = io.github.mortuusars.mortaar.Register.registrar(ID);

    public static void init() {
        Blocks.init();
        Items.init();
        BlockEntityTypes.init();
        RecipeTypes.init();
        RecipeSerializers.init();
        Stats.init();
    }

    /**
     * Creates resource location in the mod namespace with the given path.
     */
    public static Identifier identifier(String path) {
        return Identifier.fromNamespaceAndPath(ID, path);
    }

    public static class BlockIds {
        public static final ResourceKey<Block> BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("brick_chimney"));
        public static final ResourceKey<Block> DIRTY_BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_brick_chimney"));
        public static final ResourceKey<Block> COBBLESTONE_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("cobblestone_chimney"));
        public static final ResourceKey<Block> DIRTY_COBBLESTONE_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_cobblestone_chimney"));
        public static final ResourceKey<Block> STONE_BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("stone_brick_chimney"));
        public static final ResourceKey<Block> DIRTY_STONE_BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_stone_brick_chimney"));
        public static final ResourceKey<Block> MUD_BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("mud_brick_chimney"));
        public static final ResourceKey<Block> DIRTY_MUD_BRICK_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_mud_brick_chimney"));
        public static final ResourceKey<Block> IRON_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("iron_chimney"));
        public static final ResourceKey<Block> DIRTY_IRON_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_iron_chimney"));
        public static final ResourceKey<Block> COPPER_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("copper_chimney"));
        public static final ResourceKey<Block> DIRTY_COPPER_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_copper_chimney"));
        public static final ResourceKey<Block> TERRACOTTA_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("terracotta_chimney"));
        public static final ResourceKey<Block> DIRTY_TERRACOTTA_CHIMNEY = ResourceKey.create(
              Registries.BLOCK, identifier("dirty_terracotta_chimney"));
    }

    public static class Blocks {
        public static final Supplier<ChimneyBlock> BRICK_CHIMNEY = REGISTRAR.block("brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_BRICK_CHIMNEY = REGISTRAR.block("dirty_brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.BRICK));

        public static final Supplier<ChimneyBlock> COBBLESTONE_CHIMNEY = REGISTRAR.block("cobblestone_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.COBBLESTONE_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.STONE)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.COBBLESTONE));

        public static final Supplier<ChimneyBlock> DIRTY_COBBLESTONE_CHIMNEY = REGISTRAR.block("dirty_cobblestone_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_COBBLESTONE_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.STONE)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.COBBLESTONE));

        public static final Supplier<ChimneyBlock> STONE_BRICK_CHIMNEY = REGISTRAR.block("stone_brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.STONE_BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.STONE_BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_STONE_BRICK_CHIMNEY = REGISTRAR.block("dirty_stone_brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_STONE_BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.BASALT)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.STONE_BRICK));

        public static final Supplier<ChimneyBlock> MUD_BRICK_CHIMNEY = REGISTRAR.block("mud_brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.MUD_BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_BROWN)
                    .sound(SoundType.MUD_BRICKS)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.MUD_BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_MUD_BRICK_CHIMNEY = REGISTRAR.block("dirty_mud_brick_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_MUD_BRICK_CHIMNEY)
                    .mapColor(MapColor.COLOR_BROWN)
                    .sound(SoundType.MUD_BRICKS)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.MUD_BRICK));

        public static final Supplier<ChimneyBlock> IRON_CHIMNEY = REGISTRAR.block("iron_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.IRON_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.METAL)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.IRON));

        public static final Supplier<ChimneyBlock> DIRTY_IRON_CHIMNEY = REGISTRAR.block("dirty_iron_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_IRON_CHIMNEY)
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.METAL)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.IRON));

        public static final Supplier<ChimneyBlock> COPPER_CHIMNEY = REGISTRAR.block("copper_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.COPPER_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.COPPER)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.COPPER));

        public static final Supplier<ChimneyBlock> DIRTY_COPPER_CHIMNEY = REGISTRAR.block("dirty_copper_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_COPPER_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.COPPER)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.COPPER));

        public static final Supplier<ChimneyBlock> TERRACOTTA_CHIMNEY = REGISTRAR.block("terracotta_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.TERRACOTTA_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DRIPSTONE_BLOCK)
                    .strength(2f, 2f)
                    .destroyTime(2.2f)
                    .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.TERRACOTTA));

        public static final Supplier<ChimneyBlock> DIRTY_TERRACOTTA_CHIMNEY = REGISTRAR.block("dirty_terracotta_chimney",
              () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                    .setId(BlockIds.DIRTY_TERRACOTTA_CHIMNEY)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.DRIPSTONE_BLOCK)
                    .strength(2f, 2f)
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.TERRACOTTA));

        static void init() {
        }
    }

    public static class BlockEntityTypes {
        public static final Supplier<BlockEntityType<ChimneyBlockEntity>> CHIMNEY = REGISTRAR.blockEntityType("chimney",
              () -> REGISTRAR.newBlockEntityType(ChimneyBlockEntity::new,
                    Blocks.BRICK_CHIMNEY.get(),
                    Blocks.DIRTY_BRICK_CHIMNEY.get(),
                    Blocks.COBBLESTONE_CHIMNEY.get(),
                    Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(),
                    Blocks.STONE_BRICK_CHIMNEY.get(),
                    Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(),
                    Blocks.MUD_BRICK_CHIMNEY.get(),
                    Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(),
                    Blocks.IRON_CHIMNEY.get(),
                    Blocks.DIRTY_IRON_CHIMNEY.get(),
                    Blocks.COPPER_CHIMNEY.get(),
                    Blocks.DIRTY_COPPER_CHIMNEY.get(),
                    Blocks.TERRACOTTA_CHIMNEY.get(),
                    Blocks.DIRTY_TERRACOTTA_CHIMNEY.get()));

        static void init() {
        }
    }

    public static class ItemIds {
        public static final ResourceKey<Item> BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("brick_chimney"));
        public static final ResourceKey<Item> DIRTY_BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_brick_chimney"));
        public static final ResourceKey<Item> COBBLESTONE_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("cobblestone_chimney"));
        public static final ResourceKey<Item> DIRTY_COBBLESTONE_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_cobblestone_chimney"));
        public static final ResourceKey<Item> STONE_BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("stone_brick_chimney"));
        public static final ResourceKey<Item> DIRTY_STONE_BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_stone_brick_chimney"));
        public static final ResourceKey<Item> MUD_BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("mud_brick_chimney"));
        public static final ResourceKey<Item> DIRTY_MUD_BRICK_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_mud_brick_chimney"));
        public static final ResourceKey<Item> IRON_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("iron_chimney"));
        public static final ResourceKey<Item> DIRTY_IRON_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_iron_chimney"));
        public static final ResourceKey<Item> COPPER_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("copper_chimney"));
        public static final ResourceKey<Item> DIRTY_COPPER_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_copper_chimney"));
        public static final ResourceKey<Item> TERRACOTTA_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("terracotta_chimney"));
        public static final ResourceKey<Item> DIRTY_TERRACOTTA_CHIMNEY = ResourceKey.create(
              Registries.ITEM, identifier("dirty_terracotta_chimney"));
    }

    public static class Items {
        public static final Supplier<BlockItem> BRICK_CHIMNEY = REGISTRAR.item("brick_chimney",
              () -> new BlockItem(Blocks.BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_BRICK_CHIMNEY = REGISTRAR.item("dirty_brick_chimney",
              () -> new BlockItem(Blocks.DIRTY_BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> COBBLESTONE_CHIMNEY = REGISTRAR.item("cobblestone_chimney",
              () -> new BlockItem(Blocks.COBBLESTONE_CHIMNEY.get(), new Item.Properties().setId(ItemIds.COBBLESTONE_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_COBBLESTONE_CHIMNEY = REGISTRAR.item("dirty_cobblestone_chimney",
              () -> new BlockItem(Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_COBBLESTONE_CHIMNEY)));
        public static final Supplier<BlockItem> STONE_BRICK_CHIMNEY = REGISTRAR.item("stone_brick_chimney",
              () -> new BlockItem(Blocks.STONE_BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.STONE_BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_STONE_BRICK_CHIMNEY = REGISTRAR.item("dirty_stone_brick_chimney",
              () -> new BlockItem(Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_STONE_BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> MUD_BRICK_CHIMNEY = REGISTRAR.item("mud_brick_chimney",
              () -> new BlockItem(Blocks.MUD_BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.MUD_BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_MUD_BRICK_CHIMNEY = REGISTRAR.item("dirty_mud_brick_chimney",
              () -> new BlockItem(Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_MUD_BRICK_CHIMNEY)));
        public static final Supplier<BlockItem> IRON_CHIMNEY = REGISTRAR.item("iron_chimney",
              () -> new BlockItem(Blocks.IRON_CHIMNEY.get(), new Item.Properties().setId(ItemIds.IRON_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_IRON_CHIMNEY = REGISTRAR.item("dirty_iron_chimney",
              () -> new BlockItem(Blocks.DIRTY_IRON_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_IRON_CHIMNEY)));
        public static final Supplier<BlockItem> COPPER_CHIMNEY = REGISTRAR.item("copper_chimney",
              () -> new BlockItem(Blocks.COPPER_CHIMNEY.get(), new Item.Properties().setId(ItemIds.COPPER_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_COPPER_CHIMNEY = REGISTRAR.item("dirty_copper_chimney",
              () -> new BlockItem(Blocks.DIRTY_COPPER_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_COPPER_CHIMNEY)));
        public static final Supplier<BlockItem> TERRACOTTA_CHIMNEY = REGISTRAR.item("terracotta_chimney",
              () -> new BlockItem(Blocks.TERRACOTTA_CHIMNEY.get(), new Item.Properties().setId(ItemIds.TERRACOTTA_CHIMNEY)));
        public static final Supplier<BlockItem> DIRTY_TERRACOTTA_CHIMNEY = REGISTRAR.item("dirty_terracotta_chimney",
              () -> new BlockItem(Blocks.DIRTY_TERRACOTTA_CHIMNEY.get(), new Item.Properties().setId(ItemIds.DIRTY_TERRACOTTA_CHIMNEY)));

        static void init() {
        }
    }

    public static class Stats {
        public static final Supplier<Identifier> SOOT_SCRAPED = REGISTRAR.stat(identifier("soot_scraped"), StatFormatter.DEFAULT);

        public static void init() {
        }
    }

    public static class Tags {
        public static class Items {
            public static final TagKey<Item> CHIMNEYS = TagKey.create(Registries.ITEM, identifier("chimneys"));
            public static final TagKey<Item> SOOTY_CHIMNEYS = TagKey.create(Registries.ITEM, identifier("sooty_chimneys"));
            public static final TagKey<Item> C_CHIMNEYS = TagKey.create(Registries.ITEM, Identifier.parse("c:chimneys"));
            public static final TagKey<Item> SOOT_SCRAPERS = TagKey.create(Registries.ITEM, SootyChimneys.identifier("soot_scrapers"));
        }

        public static class Blocks {
            public static final TagKey<Block> CHIMNEYS = TagKey.create(Registries.BLOCK, identifier("chimneys"));
            public static final TagKey<Block> SOOTY_CHIMNEYS = TagKey.create(Registries.BLOCK, identifier("sooty_chimneys"));
            public static final TagKey<Block> C_CHIMNEYS = TagKey.create(Registries.BLOCK, Identifier.parse("c:chimneys"));
            public static final TagKey<Block> SMOKE_BLOCKING = TagKey.create(Registries.BLOCK, SootyChimneys.identifier("smoke_blocking"));
            public static final TagKey<Block> SMOKE_BOOSTING = TagKey.create(Registries.BLOCK, SootyChimneys.identifier("smoke_boosting"));
        }
    }

    public static class RecipeTypes {
        public static final Supplier<RecipeType<SootScrapingRecipe>> SOOT_SCRAPING = REGISTRAR.recipeType("soot_scraping",
              () -> new RecipeType<>() {
                  @Override
                  public String toString() {
                      return ID + ":soot_scraping";
                  }
              });

        static void init() {
        }
    }

    public static class RecipeSerializers {
        public static final Supplier<RecipeSerializer<SootScrapingRecipe>> SOOT_SCRAPING =
              REGISTRAR.recipeSerializer("soot_scraping",
                    () -> new RecipeSerializer<>(SootScrapingRecipe.CODEC, SootScrapingRecipe.STREAM_CODEC));

        static void init() {
        }
    }
}
