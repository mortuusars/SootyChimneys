package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.blocks.BrickChimneyBlock;
import io.github.mortuusars.sootychimneys.blocks.ChimneyBlock;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {

    private final ResourceLocation _brickChimneyTexture = mcLoc("block/bricks");
    private final ResourceLocation _brickChimneySootTexture = modLoc("block/brick_chimney_soot_overlay");
    private final ResourceLocation _brickChimneySootSideTexture = modLoc("block/brick_chimney_soot_side_overlay");

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SootyChimneys.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        BlockModelBuilder brickChimneyCleanModel = createBrickChimneyCleanModel();
        BlockModelBuilder brickChimneyDirtyModel = createBrickChimneyDirtyModel();

        getVariantBuilder(Registry.BRICK_CHIMNEY.get())
                .forAllStatesExcept(state ->
                        ConfiguredModel.builder()
                            .modelFile(state.getValue(ChimneyBlock.DIRTY) ?
                                    brickChimneyDirtyModel : brickChimneyCleanModel)
                            .build(), BrickChimneyBlock.LIT);
    }

    private BlockModelBuilder createBrickChimneyCleanModel() {
        return models().getBuilder(Registry.BRICK_CHIMNEY.get().getRegistryName().getPath())
                .parent(models().getExistingFile(mcLoc("block")))
                .texture("particle", _brickChimneyTexture)
                .texture("texture", _brickChimneyTexture)
                .texture("inside", _brickChimneySootTexture)
                .element()
                .from(1f, 0f, 1f)
                .to(15f, 11f, 15f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture(dir == Direction.UP ? "#inside" : "#texture"))
                .end()

                .element()
                .from(0f, 11f, 0f)
                .to(16f, 16f, 3f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(13f, 11f, 3f)
                .to(16f, 16f, 13f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(0f, 11f, 13f)
                .to(16f, 16f, 16f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(0f, 11f, 3f)
                .to(3f, 16f, 13f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end();
    }

    private BlockModelBuilder createBrickChimneyDirtyModel() {
        return models().getBuilder(Registry.BRICK_CHIMNEY.get().getRegistryName().getPath() + "_dirty")
                .parent(models().getExistingFile(mcLoc("block")))
                .texture("particle", _brickChimneySootTexture)
                .texture("texture", _brickChimneyTexture)
                .texture("overlay", _brickChimneySootSideTexture)
                .texture("overlayfull", _brickChimneySootTexture)
                .element()
                .from(1f, 0f, 1f)
                .to(15f, 11f, 15f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture(dir == Direction.UP ? "#overlayfull" : "#texture"))
                .end()
                .element()
                .from(1f, 0f, 1f)
                .to(15f, 11f, 15f)
                .face(Direction.NORTH).texture("#overlay").end()
                .face(Direction.EAST).texture("#overlay").end()
                .face(Direction.SOUTH).texture("#overlay").end()
                .face(Direction.WEST).texture("#overlay").end()
                .end()


                .element()
                .from(0f, 11f, 0f)
                .to(16f, 16f, 3f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(13f, 11f, 3f)
                .to(16f, 16f, 13f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(0f, 11f, 13f)
                .to(16f, 16f, 16f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()

                .element()
                .from(0f, 11f, 3f)
                .to(3f, 16f, 13f)
                .allFaces((dir, faceBuilder) -> faceBuilder.texture("#texture"))
                .end()


                .element()
                .from(0f, 11f, 0f)
                .to(16f, 16f, 3f)
                .face(Direction.UP).texture("#overlayfull").end()
                .face(Direction.DOWN).texture("#overlayfull").end()
                .face(Direction.NORTH).texture("#overlay").end()
                .face(Direction.EAST).texture("#overlay").end()
                .face(Direction.SOUTH).texture("#overlay").end()
                .face(Direction.WEST).texture("#overlay").end()
                .end()

                .element()
                .from(13f, 11f, 3f)
                .to(16f, 16f, 13f)
                .face(Direction.UP).texture("#overlayfull").end()
                .face(Direction.DOWN).texture("#overlayfull").end()
                .face(Direction.NORTH).texture("#overlay").end()
                .face(Direction.EAST).texture("#overlay").end()
                .face(Direction.SOUTH).texture("#overlay").end()
                .face(Direction.WEST).texture("#overlay").end()
                .end()

                .element()
                .from(0f, 11f, 13f)
                .to(16f, 16f, 16f)
                .face(Direction.UP).texture("#overlayfull").end()
                .face(Direction.DOWN).texture("#overlayfull").end()
                .face(Direction.NORTH).texture("#overlay").end()
                .face(Direction.EAST).texture("#overlay").end()
                .face(Direction.SOUTH).texture("#overlay").end()
                .face(Direction.WEST).texture("#overlay").end()
                .end()

                .element()
                .from(0f, 11f, 3f)
                .to(3f, 16f, 13f)
                .face(Direction.UP).texture("#overlayfull").end()
                .face(Direction.DOWN).texture("#overlayfull").end()
                .face(Direction.NORTH).texture("#overlay").end()
                .face(Direction.EAST).texture("#overlay").end()
                .face(Direction.SOUTH).texture("#overlay").end()
                .face(Direction.WEST).texture("#overlay").end()
                .end();
    }
}
