package com.inf1nlty.uncannybaubles;

import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.io.File;

public class UncannyBaublesMod implements ModInitializer {

    public static final String NAMESPACE = "uncannybaubles";

    public void onInitialize() {

        ModResourceManager.addResourcePackDomain(NAMESPACE);

        UBConfig.load(new File("config/uncannybaubles.cfg"));

        UBRICEvents.register();
        MITEEvents.MITE_EVENT_BUS.register(new UBFMLEvents());
    }
}