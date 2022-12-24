package io.github.mortuusars.sootychimneys.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue SMOKE_STRENGTH;
    public static final ForgeConfigSpec.DoubleValue DIRTY_CHANCE;
    public static final ForgeConfigSpec.BooleanValue WIND_ENABLED;

    public static final ForgeConfigSpec.BooleanValue ADD_SOOT_SCRAPING_TO_JEI;
    public static final ForgeConfigSpec.BooleanValue DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO;

    static{
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        SMOKE_STRENGTH = builder
                .comment("Chance of the smoke particle to spawn. 1.0 - full. 0.0 - no smoke.")
                .defineInRange("SmokeChance", 1.0d, 0d, 1.0d);

        DIRTY_CHANCE = builder
                .comment("Chimneys get dirty when a random tick occurs. (Chimney must be working (LIT) and not blocked.)")
                .comment("and when random tick occurs - this value controls the chance of a chimney becoming dirty.")
                .comment("1.0 - dirty on first random tick.")
                .comment("0.0 - chimney will never get dirty.")
                .defineInRange("DirtyChance", 0.05d, 0d, 1.0d);

        WIND_ENABLED = builder
                .comment("Enable/disable wind effect on smoke particles:")
                .define("Wind", true);

        builder.push("JEI");

        ADD_SOOT_SCRAPING_TO_JEI = builder
                .comment("Soot Scraping category will be added to JEI.")
                .define("JEISootScraping", true);

        DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO = builder
                .comment("Enables/disables 'May produce a byproduct' info in JEI Soot Scraping recipes.", "Useful when scraping loot is removed.")
                .define("DisplayJEIScrapingByproductInfo", true);

        builder.pop();

        SPEC = builder.build();
    }
}
