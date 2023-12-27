package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class ModBlocks {
    public static void init() {
        BlockBehaviour.Properties brick = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                .sound(SoundType.DEEPSLATE_BRICKS)
                .strength(2f, 2f)
                .destroyTime(2.2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.BRICK.typeId(),
                () -> new ChimneyBlock(brick, SootyChimneys.Chimney.BRICK, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(brick, SootyChimneys.Chimney.BRICK, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties cobblestone = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .sound(SoundType.STONE)
                .strength(2f, 2f)
                .destroyTime(2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.COBBLESTONE.typeId(),
                () -> new ChimneyBlock(cobblestone, SootyChimneys.Chimney.COBBLESTONE, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(cobblestone, SootyChimneys.Chimney.COBBLESTONE, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties stone_brick = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .sound(SoundType.BASALT)
                .strength(2f, 2f)
                .destroyTime(2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.STONE_BRICK.typeId(),
                () -> new ChimneyBlock(stone_brick, SootyChimneys.Chimney.STONE_BRICK, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(stone_brick, SootyChimneys.Chimney.STONE_BRICK, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties mud = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN)
                .sound(SoundType.MUD_BRICKS)
                .strength(2f, 2f)
                .destroyTime(2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.MUD_BRICK.typeId(),
                () -> new ChimneyBlock(mud, SootyChimneys.Chimney.MUD_BRICK, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(mud, SootyChimneys.Chimney.MUD_BRICK, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties iron = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
                .sound(SoundType.METAL)
                .strength(2f, 2f)
                .destroyTime(2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.IRON.typeId(),
                () -> new ChimneyBlock(iron, SootyChimneys.Chimney.IRON, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(iron, SootyChimneys.Chimney.IRON, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties copper = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE)
                .sound(SoundType.COPPER)
                .strength(2f, 2f)
                .destroyTime(2f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.COPPER.typeId(),
                () -> new ChimneyBlock(copper, SootyChimneys.Chimney.COPPER, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(copper, SootyChimneys.Chimney.COPPER, ChimneyBlock.Type.DIRTY));

        BlockBehaviour.Properties terracotta = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                .sound(SoundType.DRIPSTONE_BLOCK)
                .strength(2f, 2f)
                .destroyTime(0.6f)
                .requiresCorrectToolForDrops();
        registerBoth(SootyChimneys.Chimney.TERRACOTTA.typeId(),
                () -> new ChimneyBlock(terracotta, SootyChimneys.Chimney.TERRACOTTA, ChimneyBlock.Type.CLEAN),
                () -> new ChimneyBlock(terracotta, SootyChimneys.Chimney.TERRACOTTA, ChimneyBlock.Type.DIRTY));
    }

    private static <T extends ChimneyBlock> void registerBoth(String id, Supplier<T> cleanSupplier, Supplier<T> dirtySupplier){
        Registry.BLOCKS.register(id + "_chimney", cleanSupplier);
        Registry.BLOCKS.register("dirty_" + id + "_chimney", dirtySupplier);
    }
}
