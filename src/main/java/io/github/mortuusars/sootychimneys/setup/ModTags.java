package io.github.mortuusars.sootychimneys.setup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> CHIMNEY = ItemTags.create(new ResourceLocation("forge:chimney"));
    }

    public static class Blocks {
        public static final TagKey<Block> CHIMNEY = BlockTags.create(new ResourceLocation("forge:chimney"));
    }
}
