package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.block.*;
import io.github.mortuusars.sootychimneys.core.ChimneySmokeProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks {
    public static final ArrayList<RegistryObject<? extends ChimneyBlock>> CHIMNEYS = new ArrayList<>();

    public static final RegistryObject<BrickChimneyBlock> BRICK_CHIMNEY =
            registerChimneyBlock("brick_chimney", () -> new BrickChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1f, 0.5f, 0.25f, 0.1f, 0.25f).setSpeed(1.25f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(2.2f)
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<BrickChimneyBlock> DIRTY_BRICK_CHIMNEY =
            registerChimneyBlock("dirty_brick_chimney", () -> new BrickChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1f, 0.5f, 0.25f, 0.1f, 0.25f).setSpeed(1.1f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(2.2f)
                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<StoneBrickChimneyBlock> STONE_BRICK_CHIMNEY =
            registerChimneyBlock("stone_brick_chimney", () -> new StoneBrickChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1.2f, 0.5f, 0.025f, 0.05f, 0.025f)
                            .setIntensity(0.5f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(2f, 2f)
                            .destroyTime(2f)
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<StoneBrickChimneyBlock> DIRTY_STONE_BRICK_CHIMNEY =
            registerChimneyBlock("dirty_stone_brick_chimney", () -> new StoneBrickChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1.2f, 0.5f, 0.025f, 0.05f, 0.025f)
                            .setIntensity(0.5f).setSpeed(0.9f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(2f, 2f)
                            .destroyTime(2f)
                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<TerracottaChimneyBlock> TERRACOTTA_CHIMNEY =
            registerChimneyBlock("terracotta_chimney", () -> new TerracottaChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 0.75f, 0.5f, 0.02f, 0.05f, 0.02f)
                            .setIntensity(0.2f)
                            .setSpeed(0.65f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.DRIPSTONE_BLOCK)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(0.6f)
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<TerracottaChimneyBlock> DIRTY_TERRACOTTA_CHIMNEY =
            registerChimneyBlock("dirty_terracotta_chimney", () -> new TerracottaChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 0.75f, 0.5f, 0.02f, 0.05f, 0.02f)
                            .setIntensity(0.2f)
                            .setSpeed(0.65f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.DRIPSTONE_BLOCK)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(0.6f)
                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<CopperChimneyBlock> COPPER_CHIMNEY =
            registerChimneyBlock("copper_chimney", () -> new CopperChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1.25f, 0.5f, 0.025f, 0.05f, 0.025f)
                            .setIntensity(0.5f)
                            .setSpeed(1.2f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.COPPER)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(2f)
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<CopperChimneyBlock> DIRTY_COPPER_CHIMNEY =
            registerChimneyBlock("dirty_copper_chimney", () -> new CopperChimneyBlock(
                    new ChimneySmokeProperties(0.5f, 1.25f, 0.5f, 0.025f, 0.05f, 0.025f)
                            .setIntensity(0.5f)
                            .setSpeed(1.1f),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.COPPER)
                            .mapColor(MapColor.COLOR_ORANGE)
                            .strength(2f, 2f)
                            .destroyTime(2f)
                            .requiresCorrectToolForDrops()));

    public static void init(){}

    private static <T extends ChimneyBlock> RegistryObject<T> registerChimneyBlock(String registryName, Supplier<T> blockSupplier){
        RegistryObject<T> block = Registry.BLOCKS.register(registryName, blockSupplier);
        CHIMNEYS.add(block);
        return block;
    }
}
