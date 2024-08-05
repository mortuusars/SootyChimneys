package io.github.mortuusars.sootychimneys;

import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.block.ChimneyBlockEntity;
import io.github.mortuusars.sootychimneys.data.Chimney;
import io.github.mortuusars.sootychimneys.data.ChimneyTypes;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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

    public static void init() {
        Blocks.init();
        BlockEntityTypes.init();
        EntityTypes.init();
        Items.init();
        DataComponents.init();
        MenuTypes.init();
        RecipeTypes.init();
        RecipeSerializers.init();
        CriteriaTriggers.init();
        SoundEvents.init();
        ArgumentTypes.init();
        WorldGenFeatures.init();
        ParticleTypes.init();
    }

//    private void enqueueIMC(final InterModEnqueueEvent event) {
//        if (ModList.get().isLoaded("create")) {
//            CreateIntegration.registerMovingBehaviors();
//        }
//    }

    /**
     * Creates resource location in the mod namespace with the given path.
     */
    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static class Blocks {
        public static final Supplier<ChimneyBlock> BRICK_CHIMNEY = Register.block("brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.DEEPSLATE_BRICKS)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_BRICK_CHIMNEY = Register.block("dirty_brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.DEEPSLATE_BRICKS)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.BRICK));

        public static final Supplier<ChimneyBlock> COBBLESTONE_CHIMNEY = Register.block("cobblestone_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.STONE)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.COBBLESTONE));

        public static final Supplier<ChimneyBlock> DIRTY_COBBLESTONE_CHIMNEY = Register.block("dirty_cobblestone_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.STONE)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.COBBLESTONE));

        public static final Supplier<ChimneyBlock> STONE_BRICK_CHIMNEY = Register.block("stone_brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.BASALT)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.STONE_BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_STONE_BRICK_CHIMNEY = Register.block("dirty_stone_brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.BASALT)
                        .strength(2f, 2f)
                        .destroyTime(2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.STONE_BRICK));

        public static final Supplier<ChimneyBlock> MUD_BRICK_CHIMNEY = Register.block("mud_brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BROWN)
                        .sound(SoundType.MUD_BRICKS)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.MUD_BRICK));

        public static final Supplier<ChimneyBlock> DIRTY_MUD_BRICK_CHIMNEY = Register.block("dirty_mud_brick_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BROWN)
                        .sound(SoundType.MUD_BRICKS)
                        .strength(2f, 2f)
                        .destroyTime(2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.MUD_BRICK));

        public static final Supplier<ChimneyBlock> IRON_CHIMNEY = Register.block("iron_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.METAL)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.IRON));

        public static final Supplier<ChimneyBlock> DIRTY_IRON_CHIMNEY = Register.block("dirty_iron_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .sound(SoundType.METAL)
                        .strength(2f, 2f)
                        .destroyTime(2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.IRON));

        public static final Supplier<ChimneyBlock> COPPER_CHIMNEY = Register.block("copper_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.COPPER)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.COPPER));

        public static final Supplier<ChimneyBlock> DIRTY_COPPER_CHIMNEY = Register.block("dirty_copper_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.COPPER)
                        .strength(2f, 2f)
                        .destroyTime(2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.COPPER));

        public static final Supplier<ChimneyBlock> TERRACOTTA_CHIMNEY = Register.block("terracotta_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.DRIPSTONE_BLOCK)
                        .strength(2f, 2f)
                        .destroyTime(2.2f)
                        .requiresCorrectToolForDrops(), Chimney.State.CLEAN, ChimneyTypes.TERRACOTTA));

        public static final Supplier<ChimneyBlock> DIRTY_TERRACOTTA_CHIMNEY = Register.block("dirty_terracotta_chimney",
                () -> new ChimneyBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_ORANGE)
                        .sound(SoundType.DRIPSTONE_BLOCK)
                        .strength(2f, 2f)
                        .destroyTime(2f)
                        .requiresCorrectToolForDrops(), Chimney.State.DIRTY, ChimneyTypes.TERRACOTTA));

        static void init() { }
    }

    public static class BlockEntityTypes {
        public static final Supplier<BlockEntityType<ChimneyBlockEntity>> CHIMNEY = Register.blockEntityType("chimney",
                () -> Register.newBlockEntityType(ChimneyBlockEntity::new,
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

        static void init() { }
    }

    public static class Items {
        public static final Supplier<BlockItem> BRICK_CHIMNEY = Register.item("brick_chimney",
                () -> new BlockItem(Blocks.BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_BRICK_CHIMNEY = Register.item("dirty_brick_chimney",
                () -> new BlockItem(Blocks.DIRTY_BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> COBBLESTONE_CHIMNEY = Register.item("cobblestone_chimney",
                () -> new BlockItem(Blocks.COBBLESTONE_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_COBBLESTONE_CHIMNEY = Register.item("dirty_cobblestone_chimney",
                () -> new BlockItem(Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> STONE_BRICK_CHIMNEY = Register.item("stone_brick_chimney",
                () -> new BlockItem(Blocks.STONE_BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_STONE_BRICK_CHIMNEY = Register.item("dirty_stone_brick_chimney",
                () -> new BlockItem(Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> MUD_BRICK_CHIMNEY = Register.item("mud_brick_chimney",
                () -> new BlockItem(Blocks.MUD_BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_MUD_BRICK_CHIMNEY = Register.item("dirty_mud_brick_chimney",
                () -> new BlockItem(Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> IRON_CHIMNEY = Register.item("iron_chimney",
                () -> new BlockItem(Blocks.IRON_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_IRON_CHIMNEY = Register.item("dirty_iron_chimney",
                () -> new BlockItem(Blocks.DIRTY_IRON_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> COPPER_CHIMNEY = Register.item("copper_chimney",
                () -> new BlockItem(Blocks.COPPER_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_COPPER_CHIMNEY = Register.item("dirty_copper_chimney",
                () -> new BlockItem(Blocks.DIRTY_COPPER_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> TERRACOTTA_CHIMNEY = Register.item("terracotta_chimney",
                () -> new BlockItem(Blocks.TERRACOTTA_CHIMNEY.get(), new Item.Properties()));
        public static final Supplier<BlockItem> DIRTY_TERRACOTTA_CHIMNEY = Register.item("dirty_terracotta_chimney",
                () -> new BlockItem(Blocks.DIRTY_TERRACOTTA_CHIMNEY.get(), new Item.Properties()));

        static void init() { }
    }

    public static class DataComponents {
        static void init() { }
    }

    public static class MenuTypes {
        static void init() { }
    }

    public static class LootTables {
    }

    public static class CriteriaTriggers {
        public static void init() {
        }
    }

    public static class Stats {
        private static final Map<ResourceLocation, StatFormatter> STATS = new HashMap<>();

        @SuppressWarnings("SameParameterValue")
        private static ResourceLocation register(ResourceLocation location, StatFormatter formatter) {
            STATS.put(location, formatter);
            return location;
        }

        public static void register() {
            STATS.forEach((location, formatter) -> {
                Registry.register(BuiltInRegistries.CUSTOM_STAT, location, location);
                net.minecraft.stats.Stats.CUSTOM.get(location, formatter);
            });
        }
    }

    public static class SoundEvents {
        private static Supplier<SoundEvent> registerBlockSound(String name) {
            return Register.soundEvent("block." + ID + "." + name,
                    () -> SoundEvent.createVariableRangeEvent(SootyChimneys.resource("block." + ID + "." + name)));
        }

        static void init() { }
    }

    public static class SoundTypes {
    }

    public static class Tags {
        public static class Items {
            public static final TagKey<Item> CHIMNEYS = TagKey.create(Registries.ITEM, ResourceLocation.parse("c:chimneys"));
        }

        public static class Blocks {
            public static final TagKey<Block> CHIMNEYS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("c:chimneys"));
            public static final TagKey<Block> SMOKE_BLOCKING = TagKey.create(Registries.BLOCK, SootyChimneys.resource("smoke_blocking"));
            public static final TagKey<Block> SMOKE_BOOSTING = TagKey.create(Registries.BLOCK, SootyChimneys.resource("smoke_boosting"));
        }
    }

    public static class EntityTypes {
        static void init() { }
    }

    public static class RecipeTypes {
        public static final Supplier<RecipeType<SootScrapingRecipe>> SOOT_SCRAPING = Register.recipeType("soot_scraping",
                () -> new RecipeType<>() {
                    @Override
                    public String toString() {
                        return ID + ":soot_scraping";
                    }
                });

        static void init() { }
    }

    public static class RecipeSerializers {
        public static final Supplier<RecipeSerializer<?>> SOOT_SCRAPING =
                Register.recipeSerializer("soot_scraping", SootScrapingRecipe.Serializer::new);

        static void init() { }
    }

    public static class WorldGenFeatures {
        static void init() { }
    }

    public static class ArgumentTypes {
        public static void init() { }
    }

    public static class ParticleTypes {
        public static void init() { }
    }
}
