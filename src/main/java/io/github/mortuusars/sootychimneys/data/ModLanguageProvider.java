package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    private final String _locale;

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, SootyChimneys.MOD_ID, locale);
        _locale = locale;
    }

    @Override
    protected void addTranslations() {
        switch (_locale) {
            case "en_us" -> genEN_US();
            case "uk_ua" -> genUK_UA();
            default -> throw new IllegalArgumentException("This locale is not supported for generation.");
        }
    }

    private void genEN_US(){
        add(ModBlocks.BRICK_CHIMNEY.get(), "Brick Chimney");
        add(ModBlocks.DIRTY_BRICK_CHIMNEY.get(), "Dirty Brick Chimney");

        add(ModBlocks.STONE_BRICK_CHIMNEY.get(), "Stone Brick Chimney");
        add(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get(), "Dirty Stone Brick Chimney");

        add(ModBlocks.TERRACOTTA_CHIMNEY.get(), "Terracotta Chimney");
        add(ModBlocks.DIRTY_TERRACOTTA_CHIMNEY.get(), "Dirty Terracotta Chimney");

        add(ModBlocks.COPPER_CHIMNEY.get(), "Copper Chimney");
        add(ModBlocks.DIRTY_COPPER_CHIMNEY.get(), "Dirty Copper Chimney");

        add("message." + SootyChimneys.MOD_ID + ".blocked", "Smoke blocked");
        add("message." + SootyChimneys.MOD_ID + ".open", "Smoke flow restored");

        add("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping", "Soot Scraping");
        add("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping.soot_items_info", "May produce a byproduct");

    }

    private void genUK_UA() {
        add(ModBlocks.BRICK_CHIMNEY.get(), "Цегляний Димохід");
        add(ModBlocks.DIRTY_BRICK_CHIMNEY.get(), "Брудний Цегляний Димохід");

        add(ModBlocks.STONE_BRICK_CHIMNEY.get(), "Димохід з Кам'яної Цегли");
        add(ModBlocks.DIRTY_STONE_BRICK_CHIMNEY.get(), "Брудний Димохід з Кам'яної Цегли");

        add(ModBlocks.TERRACOTTA_CHIMNEY.get(), "Теракотовий Димохід");
        add(ModBlocks.DIRTY_TERRACOTTA_CHIMNEY.get(), "Брудний Теракотовий Димохід");

        add(ModBlocks.COPPER_CHIMNEY.get(), "Мідний Димохід");
        add(ModBlocks.DIRTY_COPPER_CHIMNEY.get(), "Брудний Мідний Димохід");

        add("message." + SootyChimneys.MOD_ID + ".blocked", "Вихід диму перекрито");
        add("message." + SootyChimneys.MOD_ID + ".open", "Вихід диму відновлено");

        add("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping", "Зішкріб Сажі");
        add("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping.soot_items_info", "Може мати додатковий продукт");
    }
}
