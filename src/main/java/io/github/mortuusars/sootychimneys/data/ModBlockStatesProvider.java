package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), SootyChimneys.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        chimneyModel(ModBlocks.BRICK_CHIMNEY);
        chimneyModel(ModBlocks.DIRTY_BRICK_CHIMNEY);

        chimneyModel(ModBlocks.STONE_BRICK_CHIMNEY);
        chimneyModel(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY);

        chimneyModel(ModBlocks.TERRACOTTA_CHIMNEY);
        chimneyModel(ModBlocks.DIRTY_TERRACOTTA_CHIMNEY);

        chimneyModel(ModBlocks.COPPER_CHIMNEY);
        chimneyModel(ModBlocks.DIRTY_COPPER_CHIMNEY);
    }

    private void chimneyModel(RegistryObject<? extends Block> blockRegistry){
        String path = Objects.requireNonNull(blockRegistry.getId()).getPath();
        getVariantBuilder(blockRegistry.get())
                .forAllStatesExcept(state -> ConfiguredModel.builder()
                        .modelFile(models().getExistingFile(modLoc("block/" + path)))
                        .build(), ChimneyBlock.LIT, ChimneyBlock.BLOCKED);
    }
}
