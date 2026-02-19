package com.inf1nlty.uncannybaubles;

import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class UncannyBaublesMod implements ModInitializer {

    public static final String NAMESPACE = "uncannybaubles";

    public void onInitialize() {

        ModResourceManager.addResourcePackDomain(NAMESPACE);

        UBConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(UBConfigs.getInstance());

        MITEEvents.MITE_EVENT_BUS.register(new UBFMLEvents());
        UBRICEvents.register();
    }
}