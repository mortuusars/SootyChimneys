package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

@SuppressWarnings("DataFlowIssue")
public class ModBlockEntities {
    public static final RegistryObject<BlockEntityType<ChimneyBlockEntity>> CHIMNEY_BLOCK_ENTITY =
            Registry.BLOCK_ENTITIES.register("chimney_block_entity",
                    () -> BlockEntityType.Builder.of(ChimneyBlockEntity::new, getValidChimneys())
                            .build(null));

    public static void init(){}

    private static Block[] getValidChimneys() {
        ArrayList<Block> list = new ArrayList<>();
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            list.add(chimney.getCleanBlock());
            list.add(chimney.getDirtyBlock());
        }
        return list.toArray(Block[]::new);
    }
}
