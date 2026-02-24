package io.github.mortuusars.sootychimneys.data;

import net.minecraft.world.phys.shapes.VoxelShape;

public record ChimneyShape(VoxelShape regular, VoxelShape stacked) { }
