package io.github.mortuusars.sootychimneys.core;

import net.minecraft.world.phys.shapes.VoxelShape;

public record ChimneyShape(VoxelShape regular, VoxelShape stacked) { }
