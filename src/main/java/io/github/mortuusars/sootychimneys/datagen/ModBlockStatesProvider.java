package io.github.mortuusars.sootychimneys.datagen;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SootyChimneys.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation textureLocation = mcLoc("block/bricks");
        simpleBlock(Registry.BRICK_CHIMNEY.get(), models().cubeAll(Registry.BRICK_CHIMNEY.get().getRegistryName().getPath(), textureLocation));
    }
}
