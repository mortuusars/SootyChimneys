package io.github.mortuusars.sootychimneys.datagen;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.setup.Registry;
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
        add(Registry.BRICK_CHIMNEY.get(), "Brick Chimney");
    }

    private void genUK_UA() {
        add(Registry.BRICK_CHIMNEY.get(), "Цегляний Димоход");
    }
}
