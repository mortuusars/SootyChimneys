package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SootyChimneys.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        chimneyModel(ModBlocks.BRICK_CHIMNEY.get());
        chimneyModel(ModBlocks.DIRTY_BRICK_CHIMNEY.get());

        chimneyModel(ModBlocks.STONE_BRICK_CHIMNEY.get());
        chimneyModel(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get());

        chimneyModel(ModBlocks.TERRACOTTA_CHIMNEY.get());
        chimneyModel(ModBlocks.DIRTY_TERRACOTTA_CHIMNEY.get());

        chimneyModel(ModBlocks.COPPER_CHIMNEY.get());
        chimneyModel(ModBlocks.DIRTY_COPPER_CHIMNEY.get());
    }

    private void chimneyModel(Block block){
        String path = Objects.requireNonNull(block.getRegistryName()).getPath();
        getVariantBuilder(block)
                .forAllStatesExcept(state -> ConfiguredModel.builder()
                        .modelFile(models().getExistingFile(modLoc("block/" + path)))
                        .build(), ChimneyBlock.LIT, ChimneyBlock.BLOCKED);
    }
}
