package io.github.mortuusars.sootychimneys;

import com.mojang.logging.LogUtils;
import io.github.mortuusars.sootychimneys.client.ClientSetup;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SootyChimneys.MOD_ID)
public class SootyChimneys
{
    public static final String MOD_ID = "sootychimneys";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean debug = true;

    public SootyChimneys()
    {
        Registry.init();
        MinecraftForge.EVENT_BUS.addListener(WindGetter::onPlayerTick);

        if (debug)
            MinecraftForge.EVENT_BUS.addListener(WindGetter::onRightClick);

        final ClientSetup clientSeup = new ClientSetup(FMLJavaModLoadingContext.get().getModEventBus());
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> clientSeup::registerClientOnlyEvents);
    }
}
