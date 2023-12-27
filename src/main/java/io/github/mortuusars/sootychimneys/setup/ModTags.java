package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> CHIMNEYS = ItemTags.create(new ResourceLocation("forge:chimneys"));
    }

    public static class Blocks {
        public static final TagKey<Block> CHIMNEYS = BlockTags.create(new ResourceLocation("forge:chimneys"));
        public static final TagKey<Block> SMOKE_BLOCKING = BlockTags.create(SootyChimneys.resource("smoke_blocking"));
        public static final TagKey<Block> SMOKE_BOOSTING = BlockTags.create(SootyChimneys.resource("smoke_boosting"));
    }
}
