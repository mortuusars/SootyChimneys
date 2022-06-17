package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.blocks.ChimneyBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final RegistryObject<BlockEntityType<ChimneyBlockEntity>> CHIMNEY_BLOCK_ENTITY =
            Registry.BLOCK_ENTITIES.register("brick_chimney",
                    () -> BlockEntityType.Builder.of(ChimneyBlockEntity::new,
                            // valid blocks for this block entity
                            new Block[] {
                                    ModBlocks.BRICK_CHIMNEY.get(),
                                    ModBlocks.DIRTY_BRICK_CHIMNEY.get()
                            }
                            ).build(null));

    public static void init(){}
}
