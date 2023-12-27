package io.github.mortuusars.sootychimneys;

import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.core.Smoke;
import io.github.mortuusars.sootychimneys.integration.create.CreateIntegration;
import io.github.mortuusars.sootychimneys.config.Config;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod(SootyChimneys.MOD_ID)
public class SootyChimneys {
    public static final String MOD_ID = "sootychimneys";
    public static final Random RANDOM = new Random();

    public SootyChimneys() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        Registry.init();
        MinecraftForge.EVENT_BUS.addListener(WindGetter::onPlayerTick);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("create")) {
            CreateIntegration.registerMovingBehaviors();
        }
    }

    public enum Chimney {
        BRICK("brick",
                new Smoke(0.5f, 1f, 0.5f, 0.25f, 0.1f, 0.25f).setSpeed(1.2f),
                Shapes.or(
                        Block.box(1, 0, 1, 15, 11, 15),
                        Block.box(0, 11, 0, 16, 16, 16)),
                Block.box(1, 0, 1, 15, 16, 15),
                0.75f),
        COBBLESTONE("cobblestone",
                      new Smoke(0.5f, 1.15f, 0.5f, 0.25f, 0.1f, 0.25f),
                Shapes.or(
                        Block.box(3, 0, 3, 13, 16, 13),
                        Block.box(1, 4, 1, 15, 16, 15)),
                        Block.box(3, 0, 3, 13, 16, 13),
                        0.75f),
        STONE_BRICK("stone_brick", new Smoke(0.5f, 1.2f, 0.5f, 0.025f, 0.05f, 0.025f).setIntensity(0.6f),
                Shapes.or(
                        Block.box(4, 0, 4, 12, 11, 12),
                        Block.box(3, 11, 3, 13, 16, 13)),
                Block.box(4, 0, 4, 12, 16, 12),
                0.75f),
        MUD_BRICK("mud_brick", new Smoke(0.5f, 1f, 0.5f, 0.15f, 0.1f, 0.15f).setSpeed(1.1f),
                Shapes.or(
                        Block.box(0, 8, 0, 16, 16, 16),
                        Block.box(0, 0, 5, 16, 8, 11),
                        Block.box(5, 0, 0, 11, 8, 16)), Shapes.or(
                Block.box(0, 0, 5, 16, 16, 11),
                Block.box(5, 0, 0, 11, 16, 16)),
                0.75f),
        IRON("iron", new Smoke(0.5f, 1.2f, 0.5f, 0.05f, 0.05f, 0.05f).setIntensity(0.6f).setSpeed(1.35f),
                Shapes.or(
                        Block.box(5, 0, 5, 11, 15, 11),
                        Block.box(4, 7, 4, 12, 15, 12)),
                Block.box(5, 0, 5, 11, 16, 11), 0.5f),
        COPPER("copper", new Smoke(0.5f, 1.25f, 0.5f, 0.025f, 0.05f, 0.025f).setIntensity(0.5f).setSpeed(1.2f),
                Shapes.or(
                        Block.box(5, 0, 5, 11, 4, 11),
                        Block.box(6, 4, 6, 10, 16, 10),
                        Block.box(5, 10, 5, 11, 14, 11)),
                Block.box(5, 0, 5, 11, 16, 11), 0.5f),
        TERRACOTTA("terracotta", new Smoke(0.5f, 0.75f, 0.5f, 0.02f, 0.05f, 0.02f).setIntensity(0.2f).setSpeed(0.65f),
                Block.box(5, 0, 5, 11, 8, 11),
                Block.box(5, 0, 5, 11, 16, 11),
                0.5f);

        private final String typeId;
        private final String cleanId;
        private final String dirtyId;
        private final Smoke smoke;
        private final VoxelShape defaultShape;
        private final VoxelShape stackedShape;
        private final float defaultScrapeChance;

        Chimney(String typeId, Smoke smoke, VoxelShape defaultShape, VoxelShape stackedShape, float defaultScrapeChance) {
            this.typeId = typeId;
            this.smoke = smoke;
            this.defaultShape = defaultShape;
            this.stackedShape = stackedShape;
            this.defaultScrapeChance = defaultScrapeChance;

            this.cleanId = typeId + "_chimney";
            this.dirtyId = "dirty_" + typeId + "_chimney";
        }

        public String typeId() {
            return typeId;
        }

        public String getCleanId() {
            return cleanId;
        }

        public String getDirtyId() {
            return dirtyId;
        }

        public Smoke getSmoke() {
            return smoke;
        }

        public VoxelShape getDefaultShape() {
            return defaultShape;
        }

        public VoxelShape getStackedShape() {
            return stackedShape;
        }

        public float getDefaultScrapeChance() {
            return defaultScrapeChance;
        }

        public ChimneyBlock getCleanBlock() {
            return (ChimneyBlock) ForgeRegistries.BLOCKS.getValue(resource(getCleanId()));
        }

        public ChimneyBlock getDirtyBlock() {
            return (ChimneyBlock) ForgeRegistries.BLOCKS.getValue(resource(getDirtyId()));
        }

        public Item getCleanItem() {
            return ForgeRegistries.ITEMS.getValue(resource(getCleanId()));
        }

        public Item getDirtyItem() {
            return ForgeRegistries.ITEMS.getValue(resource(getDirtyId()));
        }
    }
}
