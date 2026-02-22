package com.inf1nlty.uncannybaubles;

import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;

import java.util.ArrayList;
import java.util.List;

public class UBConfigs extends SimpleConfigs {

    // Drop Probabilities
    public static final ConfigDouble luckyCloverDropProbability = new ConfigDouble(
        "uncannybaubles.luckyCloverDropProbability", 0.0001, 0.0, 1.0,
        "uncannybaubles.luckyCloverDropProbability"
    );

    public static final ConfigDouble spiderBezoarDropProbability = new ConfigDouble(
        "uncannybaubles.spiderBezoarDropProbability", 0.01, 0.0, 1.0,
        "uncannybaubles.spiderBezoarDropProbability"
    );

    public static final ConfigDouble fireElementalLavaCharmDropProbability = new ConfigDouble(
        "uncannybaubles.fireElementalLavaCharmDropProbability", 0.01, 0.0, 1.0,
        "uncannybaubles.fireElementalLavaCharmDropProbability"
    );

    public static final ConfigDouble witchRegenRingDropProbability = new ConfigDouble(
        "uncannybaubles.witchRegenRingDropProbability", 0.10, 0.0, 1.0,
        "uncannybaubles.witchRegenRingDropProbability"
    );

    public static final ConfigDouble cowEternalSteakDropProbability = new ConfigDouble(
        "uncannybaubles.cowEternalSteakDropProbability", 0.0002, 0.0, 1.0,
        "uncannybaubles.cowEternalSteakDropProbability"
    );

    public static final ConfigDouble horseLuckyHorseshoeDropProbability = new ConfigDouble(
        "uncannybaubles.horseLuckyHorseshoeDropProbability", 0.05, 0.0, 1.0,
        "uncannybaubles.horseLuckyHorseshoeDropProbability"
    );

    // Chest Loot Probabilities
    public static final ConfigDouble kittySlippersTempleChestProbability = new ConfigDouble(
        "uncannybaubles.kittySlippersTempleChestProbability", 0.20, 0.0, 1.0,
        "uncannybaubles.kittySlippersTempleChestProbability"
    );

    public static final ConfigDouble fierceKittySlippersDungeonChestProbability = new ConfigDouble(
        "uncannybaubles.fierceKittySlippersDungeonChestProbability", 0.15, 0.0, 1.0,
        "uncannybaubles.fierceKittySlippersDungeonChestProbability"
    );

    public static final ConfigDouble pocketPistonBlacksmithChestProbability = new ConfigDouble(
        "uncannybaubles.pocketPistonBlacksmithChestProbability", 0.5, 0.0, 1.0,
        "uncannybaubles.pocketPistonBlacksmithChestProbability"
    );

    public static final ConfigDouble lavaCharmFortressChestProbability = new ConfigDouble(
        "uncannybaubles.lavaCharmFortressChestProbability", 0.15, 0.0, 1.0,
        "uncannybaubles.lavaCharmFortressChestProbability"
    );

    public static final ConfigDouble bottledCloudDungeonChestProbability = new ConfigDouble(
        "uncannybaubles.bottledCloudDungeonChestProbability", 0.05, 0.0, 1.0,
        "uncannybaubles.bottledCloudDungeonChestProbability"
    );

    public static final ConfigDouble regenRingSwampHutChestProbability = new ConfigDouble(
        "uncannybaubles.regenRingSwampHutChestProbability", 0.15, 0.0, 1.0,
        "uncannybaubles.regenRingSwampHutChestProbability"
    );

    public static final ConfigDouble waterWalkerFishingProbability = new ConfigDouble(
        "uncannybaubles.waterWalkerFishingProbability", 0.15, 0.0, 1.0,
        "uncannybaubles.waterWalkerFishingProbability"
    );

    // Other Settings
    public static final ConfigInteger eternalSteakCooldownTicks = new ConfigInteger(
        "uncannybaubles.eternalSteakCooldownTicks", 300, 0, 43200,
        "uncannybaubles.eternalSteakCooldownTicks"
    );

    private static final UBConfigs Instance;

    public static final List<ConfigBase<?>> DropProbabilities;
    public static final List<ConfigBase<?>> ChestLoot;
    public static final List<ConfigBase<?>> OtherSettings;
    public static final List<ConfigBase<?>> Total;
    public static final List<ConfigTab> tabs;

    public UBConfigs(String name, List<ConfigBase<?>> values) {
        super(name, null, values);
    }

    public List<ConfigTab> getConfigTabs() {
        return tabs;
    }

    public static UBConfigs getInstance() {
        return Instance;
    }

    static {
        Total = new ArrayList<>();
        tabs = new ArrayList<>();

        DropProbabilities = List.of(
            luckyCloverDropProbability,
            spiderBezoarDropProbability,
            fireElementalLavaCharmDropProbability,
            witchRegenRingDropProbability,
            cowEternalSteakDropProbability,
                horseLuckyHorseshoeDropProbability
        );

        ChestLoot = List.of(
            kittySlippersTempleChestProbability,
            fierceKittySlippersDungeonChestProbability,
            pocketPistonBlacksmithChestProbability,
            lavaCharmFortressChestProbability,
            bottledCloudDungeonChestProbability,
            regenRingSwampHutChestProbability,
            waterWalkerFishingProbability
        );

        OtherSettings = List.of(
            eternalSteakCooldownTicks
        );

        Total.addAll(DropProbabilities);
        Total.addAll(ChestLoot);
        Total.addAll(OtherSettings);

        tabs.add(new ConfigTab("drops", DropProbabilities));
        tabs.add(new ConfigTab("chests", ChestLoot));
        tabs.add(new ConfigTab("other", OtherSettings));

        Instance = new UBConfigs("UncannyBaubles", Total);
    }
}