package io.github.mortuusars.sootychimneys;


import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static class Common {
        public static final ForgeConfigSpec.DoubleValue SMOKE_STRENGTH;
        public static final ForgeConfigSpec.DoubleValue DIRTY_CHANCE;
        public static final ForgeConfigSpec.BooleanValue WIND_ENABLED;
        public static final ForgeConfigSpec.DoubleValue WIND_STRENGTH;
        public static final ForgeConfigSpec.BooleanValue WIND_AFFECTS_CAMPFIRE;

        public static final ForgeConfigSpec SPEC;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            SMOKE_STRENGTH = builder
                    .comment("Chance of the smoke particle to spawn. 1.0 - full. 0.0 - no smoke. Default: 0.75")
                    .defineInRange("SmokeFlow", 0.75, 0.0, 1.0);


            DIRTY_CHANCE = builder
                    .comment("Chimneys get dirty when a random tick occurs. (Chimney must be working (LIT) and not 'BLOCKED')")
                    .comment("and when random tick occurs - this value controls the chance of a chimney becoming dirty.")
                    .comment("1.0 - dirty on first random tick.")
                    .comment("0.0 - chimney will never get dirty.")
                    .comment("Default: 0.05")
                    .defineInRange("DirtyChance", 0.05, 0.0, 1.0);

            WIND_ENABLED = builder
                    .comment("Smoke particles are affected by wind. Default: true")
                    .define("Wind", true);


            WIND_STRENGTH = builder
                    .comment("How much effect wind has on the smoke. Default: 1.0")
                    .defineInRange("WindStrength", 1.0, 0.0, Double.MAX_VALUE);

            WIND_AFFECTS_CAMPFIRE = builder
                    .comment("Wind will affect smoke from Campfires. Default: true")
                    .define("WindAffectsCampfire", true);

            SPEC = builder.build();
        }
    }

    public static class Client {
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.BooleanValue ADD_SOOT_COVERING_TO_JEI;
        public static final ForgeConfigSpec.BooleanValue ADD_SOOT_SCRAPING_TO_JEI;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            builder.push("JEI");

            ADD_SOOT_COVERING_TO_JEI = builder
                    .comment("Soot Covering category will be added to JEI.")
                    .define("SootCoveringCategory", true);

            ADD_SOOT_SCRAPING_TO_JEI = builder
                    .comment("Soot Scraping category will be added to JEI.")
                    .define("SootScrapingCategory", true);

            builder.pop();

            SPEC = builder.build();
        }
    }
}