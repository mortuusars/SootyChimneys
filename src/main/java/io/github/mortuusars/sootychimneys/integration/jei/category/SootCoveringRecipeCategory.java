package io.github.mortuusars.sootychimneys.integration.jei.category;

//import io.github.mortuusars.sootychimneys.SootyChimneys;
//import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
//import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
//import io.github.mortuusars.sootychimneys.integration.jei.drawable.ChimneySmokeAnimatedDrawable;
//import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
//import io.github.mortuusars.sootychimneys.integration.jei.renderer.ScalableItemStackRenderer;
//import io.github.mortuusars.sootychimneys.setup.ModItems;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.RecipeType;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//
//import java.util.Map;

public class SootCoveringRecipeCategory /*implements IRecipeCategory<SootCoveringJeiRecipe>*/ {
//    public static final ResourceLocation UID = SootyChimneys.resource("soot_scraping");

//    public static final int BG_WIDTH = 153;
//    public static final int BG_HEIGHT = 65;
//
//    public static final Map<Item, Integer> CHIMNEY_SMOKE_Y_ORIGIN = Map.of(
//            ModItems.BRICK_CHIMNEY.get(), 3,
//            ModItems.DIRTY_BRICK_CHIMNEY.get(), 3,
//            ModItems.STONE_BRICK_CHIMNEY.get(), 0,
//            ModItems.DIRTY_STONE_BRICK_CHIMNEY.get(), 0,
//            ModItems.TERRACOTTA_CHIMNEY.get(), 10,
//            ModItems.DIRTY_TERRACOTTA_CHIMNEY.get(), 10,
//            ModItems.COPPER_CHIMNEY.get(), -1,
//            ModItems.DIRTY_COPPER_CHIMNEY.get(), -1);
//
//    private final Component title;
//    private final IDrawable background;
//    private final IDrawableStatic icon;
//    private IGuiHelper helper;
//
//    public SootCoveringRecipeCategory(IGuiHelper helper) {
//        this.helper = helper;
//        title = Component.translatable("jei." + SootyChimneys.MOD_ID + ".category.soot_covering");
//
//        ResourceLocation texture = SootyChimneys.resource("textures/gui/jei/soot_covering.png");
//
//        background = helper.createDrawable(texture, 0, 0, BG_WIDTH, BG_HEIGHT);
//        icon = helper.drawableBuilder(SootyChimneys.resource("textures/gui/jei/soot_covering_icon.png"), 0, 0, 16, 16)
//                .setTextureSize(16, 16)
//                .build();
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, SootCoveringJeiRecipe recipe, IFocusGroup focuses) {
//        // Get smoke properties:
//        ChimneySmokeAnimatedDrawable ingredientSmoke = new ChimneySmokeAnimatedDrawable(helper);
//        ChimneySmokeAnimatedDrawable resultSmoke = new ChimneySmokeAnimatedDrawable(helper);
//
//        try {
//            BlockItem ingredientChimneyBlockItem = (BlockItem) recipe.getIngredientChimney().getItem();
//            float ingredientChimneySpeed = ((ChimneyBlock)ingredientChimneyBlockItem.getBlock()).getSmokeProperties().getSpeed();
//            float ingredientChimneyIntensity = ((ChimneyBlock)ingredientChimneyBlockItem.getBlock()).getSmokeProperties().getIntensity();
//
//            BlockItem resultChimneyBlockItem = (BlockItem) recipe.getResultChimney().getItem();
//            float resultChimneySpeed = ((ChimneyBlock)resultChimneyBlockItem.getBlock()).getSmokeProperties().getSpeed();
//            float resultChimneyIntensity = ((ChimneyBlock)resultChimneyBlockItem.getBlock()).getSmokeProperties().getIntensity();
//
//            ingredientSmoke.setSpeed(ingredientChimneySpeed);
//            ingredientSmoke.setIntensity(ingredientChimneyIntensity);
//
//            resultSmoke.setSpeed(resultChimneySpeed);
//            resultSmoke.setIntensity(resultChimneyIntensity);
//        }
//        catch (Exception ignored) {}
//
//        Integer yOffset = CHIMNEY_SMOKE_Y_ORIGIN.get(recipe.getIngredientChimney().getItem());
//        builder.addSlot(RecipeIngredientRole.INPUT, 10, 18)
//                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
//                .setOverlay(ingredientSmoke, (int) ((16 * 2.5f) / 2 - 8), yOffset)
//                .addItemStack(recipe.getIngredientChimney())
//                .setSlotName("CleanChimney");
//
//        Integer yOffset1 = CHIMNEY_SMOKE_Y_ORIGIN.get(recipe.getResultChimney().getItem());
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 18)
//                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
//                .setOverlay(resultSmoke, (int) ((16 * 2.5f) / 2 - 8), yOffset1)
//                .addItemStack(recipe.getResultChimney())
//                .setSlotName("DirtyChimney");
//    }
//
//    @Override
//    public Component getTitle() {
//        return title;
//    }
//    public IDrawable getBackground() {
//        return background;
//    }
//    @Override
//    public IDrawable getIcon() {
//        return icon;
//    }
//
////    @SuppressWarnings("removal")
////    @Override
////    public ResourceLocation getUid() {
////        return UID;
////    }
////    @SuppressWarnings("removal")
////    @Override
////    public Class<? extends SootCoveringJeiRecipe> getRecipeClass() {
////        return this.getRecipeType().getRecipeClass();
////    }
//    @Override
//    public RecipeType<SootCoveringJeiRecipe> getRecipeType() {
//        return JeiRecipeTypes.SOOT_COVERING;
//    }
}
