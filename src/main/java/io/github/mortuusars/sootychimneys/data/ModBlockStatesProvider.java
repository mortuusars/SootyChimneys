package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.blocks.BrickChimneyBlock;
import io.github.mortuusars.sootychimneys.blocks.ChimneyBlock;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ModBlockStatesProvider extends BlockStateProvider {

    private final ResourceLocation _brickChimneyTexture = mcLoc("block/bricks");
    private final ResourceLocation _brickChimneySootTexture = modLoc("block/bricks_soot");
    private final ResourceLocation _brickChimneySootSideTexture = modLoc("block/bricks_soot_side");

    private final ResourceLocation _stoneChimneyTexture = mcLoc("block/stone_bricks");
    private final ResourceLocation _stoneChimneySootTexture = modLoc("block/stone_bricks_soot");
    private final ResourceLocation _stoneChimneySootSideTexture = modLoc("block/stone_bricks_soot_side");

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SootyChimneys.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        BlockModelBuilder brickChimneyCleanModel = createBrickChimneyCleanModel();
        BlockModelBuilder brickChimneyDirtyModel = createBrickChimneyDirtyModel();

        getVariantBuilder(ModBlocks.BRICK_CHIMNEY.get())
                .forAllStatesExcept(state ->
                        ConfiguredModel.builder()
                                .modelFile(brickChimneyCleanModel)
                                .build(), BrickChimneyBlock.LIT);

        getVariantBuilder(ModBlocks.DIRTY_BRICK_CHIMNEY.get())
                .forAllStatesExcept(state ->
                        ConfiguredModel.builder()
                                .modelFile(brickChimneyDirtyModel)
                                .build(), BrickChimneyBlock.LIT);

        getVariantBuilder(ModBlocks.STONE_BRICK_CHIMNEY.get())
                .forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(
                                        createStoneChimneyCleanModel(getBuilderFor(ModBlocks.STONE_BRICK_CHIMNEY.get())))
                                .build(), BrickChimneyBlock.LIT);

        getVariantBuilder(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get())
                .forAllStatesExcept(state ->
                        ConfiguredModel.builder()
                                .modelFile(createStoneChimneyDirtyModel(
                                        createStoneChimneyCleanModel(
                                                getBuilderFor(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get()))))
                                .build(), BrickChimneyBlock.LIT);
    }

    private BlockModelBuilder getBuilderFor(Block block) {
        return models().getBuilder(Objects.requireNonNull(block.getRegistryName()).getPath());
    }

    private BlockModelBuilder createBrickChimneyCleanModel() {
        return models().getBuilder(ModBlocks.BRICK_CHIMNEY.get().getRegistryName().getPath())
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
        return models().getBuilder("dirty_" + ModBlocks.BRICK_CHIMNEY.get().getRegistryName().getPath())
                .parent(models().getExistingFile(mcLoc("block")))
                .texture("particle", _brickChimneyTexture)
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

    private BlockModelBuilder createStoneChimneyCleanModel(BlockModelBuilder model) {
        model.parent(models().getExistingFile(mcLoc("block")))
                .texture("particle", _stoneChimneyTexture)
                .texture("texture", _stoneChimneyTexture)
                .texture("soot", _stoneChimneySootTexture);

        cubeAllFaces(model, 5, 0, 5, 6, 9, 6,
                ((dir, face) -> face.texture(dir == Direction.UP ? "#soot" : "#texture").uvs(0, 0, 8, 8)));

        BiConsumer<Direction, BlockModelBuilder.ElementBuilder.FaceBuilder> faceBuilderFunc = ((dir, face) ->
        {
            if (dir == Direction.UP || dir == Direction.DOWN)
                face.texture("#texture").uvs(1, 1, 3, 3);
            else
                face.texture("#texture");
        });

        cubeAllFaces(model, 4, 9, 4, 8, 7, 2, faceBuilderFunc);
        cubeAllFaces(model, 4, 9, 10, 8, 7, 2, faceBuilderFunc);
        cubeAllFaces(model, 4, 9, 6, 2, 7, 4, faceBuilderFunc);
        cubeAllFaces(model, 10, 9, 6, 2, 7, 4, faceBuilderFunc);

        return model;
    }

    private BlockModelBuilder createStoneChimneyDirtyModel(BlockModelBuilder cleanModel) {
        cleanModel.texture("soot_side", _stoneChimneySootSideTexture);

        cubeAllFaces(cleanModel, 5, 0, 5, 6, 9, 6,
                ((dir, face) -> face.texture(dir == Direction.UP ? "#soot" : "#soot_side").uvs(0, 8, 8, 16)));

        BiConsumer<Direction, BlockModelBuilder.ElementBuilder.FaceBuilder> faceBuilderFunc = ((dir, face) ->
        {
            if (dir == Direction.UP || dir == Direction.DOWN)
                face.texture("#soot").uvs(1, 1, 3, 3);
            else
                face.texture("#soot_side").uvs(2,2, 9,9);
        });

        cubeAllFaces(cleanModel, 4, 9, 4, 8, 7, 2, faceBuilderFunc);
        cubeAllFaces(cleanModel, 4, 9, 10, 8, 7, 2, faceBuilderFunc);
        cubeAllFaces(cleanModel, 4, 9, 6, 2, 7, 4, faceBuilderFunc);
        cubeAllFaces(cleanModel, 10, 9, 6, 2, 7, 4, faceBuilderFunc);

        return cleanModel;
    }

    private BlockModelBuilder cubeAllFaces(BlockModelBuilder builder,
                                           float originX, float originY, float originZ,
                                           float xLength, float yLength, float zLength,
                                           String textureName) {
        return builder
                .element()
                .from(originX, originY, originZ)
                .to(originX + xLength, originY + yLength, originZ + zLength)
                .allFaces(((direction, faceBuilder) -> faceBuilder.texture(textureName)))
                .end();
    }

    private BlockModelBuilder cubeAllFaces(BlockModelBuilder builder,
                                           float originX, float originY, float originZ,
                                           float xLength, float yLength, float zLength,
                                           BiConsumer<Direction, BlockModelBuilder.ElementBuilder.FaceBuilder> facesBuilder) {
        return builder
                .element()
                .from(originX, originY, originZ)
                .to(originX + xLength, originY + yLength, originZ + zLength)
                .allFaces(facesBuilder)
                .end();
    }
}
