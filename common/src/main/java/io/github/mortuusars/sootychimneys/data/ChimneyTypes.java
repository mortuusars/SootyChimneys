package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.data.smoke.ParticleOrigin;
import io.github.mortuusars.sootychimneys.data.smoke.ParticleSpread;
import io.github.mortuusars.sootychimneys.data.smoke.SmokeProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;

public class ChimneyTypes {
    public static final ChimneyType BRICK = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.25f, 0.5f), ParticleSpread.of(0.25f, 0.1f, 0.25f))
                    .withSpeed(1.2f),
            new ChimneyShape(
                    Shapes.or(Block.box(1, 0, 1, 15, 11, 15), Block.box(0, 11, 0, 16, 16, 16)),
                    Block.box(1, 0, 1, 15, 16, 15)));

    public static final ChimneyType COBBLESTONE = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.25f, 0.5f), ParticleSpread.of(0.15f, 0.1f, 0.15f)),
            new ChimneyShape(
                    Shapes.or(Block.box(3, 0, 3, 13, 16, 13), Block.box(1, 4, 1, 15, 16, 15)),
                    Block.box(3, 0, 3, 13, 16, 13)));

    public static final ChimneyType STONE_BRICK = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.2f, 0.5f), ParticleSpread.of(0.025f, 0.05f, 0.025f))
                    .withIntensity(0.65f),
            new ChimneyShape(
                    Shapes.or(Block.box(4, 0, 4, 12, 11, 12), Block.box(3, 11, 3, 13, 16, 13)),
                    Block.box(4, 0, 4, 12, 16, 12)));

    public static final ChimneyType MUD_BRICK = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.25f, 0.5f), ParticleSpread.of(0.15f, 0.1f, 0.15f))
                    .withSpeed(1.1f),
            new ChimneyShape(
                    Shapes.or(Block.box(0, 8, 0, 16, 16, 16), Block.box(0, 0, 5, 16, 8, 11), Block.box(5, 0, 0, 11, 8, 16)),
                    Shapes.or(Block.box(0, 0, 5, 16, 16, 11), Block.box(5, 0, 0, 11, 16, 16))));

    public static final ChimneyType IRON = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.2f, 0.5f), ParticleSpread.of(0.05f, 0.05f, 0.05f))
                    .withIntensity(0.65f)
                    .withSpeed(1.35f),
            new ChimneyShape(
                    Shapes.or(Block.box(5, 0, 5, 11, 15, 11), Block.box(4, 7, 4, 12, 15, 12)),
                    Block.box(5, 0, 5, 11, 16, 11)));

    public static final ChimneyType COPPER = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 1.3f, 0.5f), ParticleSpread.of(0.025f, 0.05f, 0.025f))
                    .withIntensity(0.55f)
                    .withSpeed(1.2f),
            new ChimneyShape(
                    Shapes.or(Block.box(5, 0, 5, 11, 4, 11), Block.box(6, 4, 6, 10, 16, 10), Block.box(5, 10, 5, 11, 14, 11)),
                    Block.box(5, 0, 5, 11, 16, 11)));

    public static final ChimneyType TERRACOTTA = new ChimneyType(
            new SmokeProperties(ParticleOrigin.of(0.5f, 0.8f, 0.5f), ParticleSpread.of(0.02f, 0.05f, 0.02f))
                    .withIntensity(0.3f)
                    .withSpeed(0.65f),
            new ChimneyShape(
                    Block.box(5, 0, 5, 11, 8, 11),
                    Block.box(5, 0, 5, 11, 16, 11)));
}
