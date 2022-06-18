package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.blocks.ChimneyBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final RegistryObject<BlockEntityType<ChimneyBlockEntity>> CHIMNEY_BLOCK_ENTITY =
            Registry.BLOCK_ENTITIES.register("brick_chimney",
                    () -> BlockEntityType.Builder.of(ChimneyBlockEntity::new, getValidChimneys())
                            .build(null));

    public static void init(){}

    private static Block[] getValidChimneys(){
        Block[] arr = new Block[ModBlocks.CHIMNEYS_LIST.size()];
        return ModBlocks.CHIMNEYS_LIST.stream().map(RegistryObject::get).toList().toArray(arr);
    }
}
