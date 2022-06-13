package io.github.mortuusars.sootychimneys;

import com.mojang.logging.LogUtils;
import io.github.mortuusars.sootychimneys.core.WindGetter;
import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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
    }
}
