package io.github.mortuusars.sootychimneys;

import com.mojang.logging.LogUtils;
import io.github.mortuusars.sootychimneys.compat.create.CreateCompat;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SootyChimneys.MOD_ID)
public class SootyChimneys
{
    public static final String MOD_ID = "sootychimneys";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean debug = false;

    public SootyChimneys()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        Registry.init();
        MinecraftForge.EVENT_BUS.addListener(WindGetter::onPlayerTick);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        if (debug)
            MinecraftForge.EVENT_BUS.addListener(WindGetter::onRightClick);

        // Not used for now.
        // final ClientSetup clientSetup = new ClientSetup(FMLJavaModLoadingContext.get().getModEventBus());
        // DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> clientSetup::registerClientOnlyEvents);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        if (ModList.get().isLoaded("create")) {
            CreateCompat.registerMovingBehaviors();
        }
    }
}
