package io.github.mortuusars.sootychimneys.integration.jei.category;

//import com.google.common.collect.ImmutableList;
//import com.mojang.blaze3d.vertex.PoseStack;
//import io.github.mortuusars.sootychimneys.SootyChimneys;
//import io.github.mortuusars.sootychimneys.config.CommonConfig;
//import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
//import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootScrapingJeiRecipe;
//import io.github.mortuusars.sootychimneys.integration.jei.renderer.ScalableItemStackRenderer;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.RecipeType;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//
//import java.util.Collections;
//import java.util.List;

@SuppressWarnings("removal")
public class SootScrapingRecipeCategory /*implements IRecipeCategory<SootScrapingJeiRecipe>*/ {
//    public static final ResourceLocation UID = SootyChimneys.resource("soot_scraping");

//    public static final int BG_WIDTH = 153;
//    public static final int BG_HEIGHT = 65;
//
//    private final List<Component> BYPRODUCT_ITEMS_INFO = ImmutableList.of(
//            Component.translatable("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping.soot_items_info"));
//
//    private final Component title;
//    private final IDrawable background;
//    private final IDrawableStatic icon;
//    private final IDrawableStatic byproductInfo;
//
//    private final int byproductXPos = 70;
//    private final int byproductYPos = 37;
//
//    public SootScrapingRecipeCategory(IGuiHelper helper) {
//        title = Component.translatable("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping");
//
//        ResourceLocation texture = SootyChimneys.resource("textures/gui/jei/soot_scraping.png");
//
//        background = helper.createDrawable(texture, 0, 0, BG_WIDTH, BG_HEIGHT);
//        icon = helper.drawableBuilder(SootyChimneys.resource("textures/gui/jei/soot_scraping_icon.png"), 0, 0, 16, 16)
//                .setTextureSize(16, 16)
//                .build();
//
//        byproductInfo = helper.createDrawable(texture, 153, 0, 14, 25);
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, SootScrapingJeiRecipe recipe, IFocusGroup focuses) {
//        builder.addSlot(RecipeIngredientRole.INPUT, 10, 18)
//                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
//                .addItemStack(recipe.getIngredientChimney())
//                .setSlotName("DirtyChimney");
//
//        builder.addSlot(RecipeIngredientRole.CATALYST, 44, 1)
//                        .addIngredients(VanillaTypes.ITEM_STACK, recipe.getTools());
//
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 18)
//                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
//                .addItemStack(recipe.getResultChimney())
//                .setSlotName("CleanChimney");
//    }
//
//    @Override
//    public void draw(SootScrapingJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
//        if (CommonConfig.DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO.get()) {
//            byproductInfo.draw(stack, byproductXPos, byproductYPos);
//        }
//    }
//
//    @Override
//    public List<Component> getTooltipStrings(SootScrapingJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
//        if (CommonConfig.DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO.get()) {
//            if ((mouseX >= byproductXPos && mouseX < byproductXPos + byproductInfo.getWidth())
//                    && (mouseY >= byproductYPos && mouseY < byproductYPos + byproductInfo.getHeight()))
//                return BYPRODUCT_ITEMS_INFO;
//        }
//
//        return Collections.emptyList();
//    }
//
//    @Override
//    public Component getTitle() {
//        return title;
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Override
//    public IDrawable getIcon() {
//        return icon;
//    }
//
////    @Override
////    public ResourceLocation getUid() {
////        return UID;
////    }
////
////    @Override
////    public Class<? extends SootScrapingJeiRecipe> getRecipeClass() {
////        return this.getRecipeType().getRecipeClass();
////    }
//
//    @Override
//    public RecipeType<SootScrapingJeiRecipe> getRecipeType() {
//        return JeiRecipeTypes.SOOT_SCRAPING;
//    }
}
