package com.inf1nlty.uncannybaubles.compat;

import com.inf1nlty.uncannybaubles.UBConfigs;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> UBConfigs.getInstance().getConfigScreen(parent);
    }
}