package com.inf1nlty.uncannybaubles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public final class UBConfig {

    private static final String DEFAULT_FILENAME = "config/uncannybaubles.cfg";

    public static double luckyCloverDropProbability = 0.001;
    public static double spiderBezoarDropProbability = 0.01;
    public static double fireElementalLavaCharmDropProbability = 0.01;
    public static double witchRegenRingDropProbability = 0.10;
    public static double cowEternalSteakDropProbability = 0.002;
    public static int eternalSteakCooldownTicks = 300;
    public static double muleLuckyHorseshoeDropProbability = 0.10;
    public static double kittySlippersTempleChestProbability = 0.20;
    public static double fierceKittySlippersDungeonChestProbability = 0.15;
    public static double pocketPistonBlacksmithChestProbability = 0.5;
    public static double lavaCharmFortressChestProbability = 0.15;
    public static double bottledCloudDungeonChestProbability = 0.15;
    public static double regenRingSwampHutChestProbability = 0.15;
    public static double waterWalkerFishingProbability = 0.15;

    private static File configFile = new File(DEFAULT_FILENAME);
    private static long lastModified = 0L;

    private UBConfig() {}

    public static synchronized void load(File file) {
        if (file == null) file = new File(DEFAULT_FILENAME);
        configFile = file;

        if (configFile.getParentFile() != null && !configFile.getParentFile().exists()) {
            if (!configFile.getParentFile().mkdirs()) {
                System.err.println("[UBConfig] Failed to create config directory");
            }
        }

        Properties props = new Properties();
        if (configFile.exists()) {
            try (InputStream in = new FileInputStream(configFile)) {
                props.load(in);
            } catch (IOException e) {
                System.err.println("[UBConfig] Failed to read config, using defaults: " + e.getMessage());
            }
        }

        luckyCloverDropProbability = parseDouble(props.getProperty("luckyCloverDropProbability"), luckyCloverDropProbability);
        spiderBezoarDropProbability = parseDouble(props.getProperty("spiderNiuHuangDropProbability"), spiderBezoarDropProbability);
        fireElementalLavaCharmDropProbability = parseDouble(props.getProperty("fireElementalLavaCharmDropProbability"), fireElementalLavaCharmDropProbability);
        witchRegenRingDropProbability = parseDouble(props.getProperty("witchRegenRingDropProbability"), witchRegenRingDropProbability);
        cowEternalSteakDropProbability = parseDouble(props.getProperty("cowEternalSteakDropProbability"), cowEternalSteakDropProbability);
        eternalSteakCooldownTicks = parseInt(props.getProperty("eternalSteakCooldownTicks"), eternalSteakCooldownTicks);
        muleLuckyHorseshoeDropProbability = parseDouble(props.getProperty("muleLuckyHorseshoeDropProbability"), muleLuckyHorseshoeDropProbability);
        kittySlippersTempleChestProbability = parseDouble(props.getProperty("kittySlippersTempleChestProbability"), kittySlippersTempleChestProbability);
        fierceKittySlippersDungeonChestProbability = parseDouble(props.getProperty("fierceKittySlippersDungeonChestProbability"), fierceKittySlippersDungeonChestProbability);
        pocketPistonBlacksmithChestProbability = parseDouble(props.getProperty("pocketPistonBlacksmithChestProbability"), pocketPistonBlacksmithChestProbability);
        lavaCharmFortressChestProbability = parseDouble(props.getProperty("lavaCharmFortressChestProbability"), lavaCharmFortressChestProbability);
        bottledCloudDungeonChestProbability = parseDouble(props.getProperty("bottledCloudDungeonChestProbability"), bottledCloudDungeonChestProbability);
        regenRingSwampHutChestProbability = parseDouble(props.getProperty("regenRingSwampHutChestProbability"), regenRingSwampHutChestProbability);
        waterWalkerFishingProbability = parseDouble(props.getProperty("waterWalkerFishingProbability"), waterWalkerFishingProbability);

        try {
            String sb = "# luckyCloverDropProbability:\n" +
                    "# The probability of a normal creature dropping a four-leaf clover upon death. (0.0 - 1.0)\n" +
                    "# 普通生物死亡时掉落四叶草的概率 (0.0 - 1.0)\n" +
                    "luckyCloverDropProbability=" + luckyCloverDropProbability + '\n' +
                    "\n" +
                    "# spiderBezoarDropProbability:\n" +
                    "# The probability of a spider dropping the Bezoar item upon death. (0.0 - 1.0)\n" +
                    "# 蜘蛛死亡时掉落牛黄的概率 (0.0 - 1.0)\n" +
                    "spiderBezoarDropProbability=" + spiderBezoarDropProbability + '\n' +
                    "\n" +
                    "# fireElementalLavaCharmDropProbability:\n" +
                    "# The probability of a fire elemental dropping the Lava Charm upon death. (0.0 - 1.0)\n" +
                    "# 火元素死亡时掉落熔岩护身符的概率 (0.0 - 1.0)\n" +
                    "fireElementalLavaCharmDropProbability=" + fireElementalLavaCharmDropProbability + '\n' +
                    "\n" +
                    "# witchRegenRingDropProbability:\n" +
                    "# The probability of a witch dropping the Regeneration Ring upon death. (0.0 - 1.0)\n" +
                    "# 女巫死亡时掉落再生戒指的概率 (0.0 - 1.0)\n" +
                    "witchRegenRingDropProbability=" + witchRegenRingDropProbability + '\n' +
                    "\n" +
                    "# cowEternalSteakDropProbability:\n" +
                    "# The probability of a cow dropping the Eternal Steak upon death. (0.0 - 1.0)\n" +
                    "# 牛死亡时掉落永恒牛排的概率 (0.0 - 1.0)\n" +
                    "cowEternalSteakDropProbability=" + cowEternalSteakDropProbability + '\n' +
                    "\n" +
                    "# eternalSteakCooldownTicks:\n" +
                    "# The cooldown time in ticks for eating Eternal Steak. (20 ticks = 1 second)\n" +
                    "# 永恒牛排的冷却时间（以tick为单位，20 tick = 1 秒）\n" +
                    "eternalSteakCooldownTicks=" + eternalSteakCooldownTicks + '\n' +
                    "\n" +
                    "# muleLuckyHorseshoeDropProbability:\n" +
                    "# The probability of a mule (horse + donkey hybrid) dropping the Lucky Horseshoe upon death. (0.0 - 1.0)\n" +
                    "# 骡子（马和驴杂交）死亡时掉落幸运马掌的概率 (0.0 - 1.0)\n" +
                    "muleLuckyHorseshoeDropProbability=" + muleLuckyHorseshoeDropProbability + '\n' +
                    "\n" +
                    "# kittySlippersTempleChestProbability:\n" +
                    "# The ACTUAL probability of finding Kitty Slippers in desert temple (8 draws). (0.0 - 1.0)\n" +
                    "# Default 0.20 = 20% in desert (weight=2, total=69), ~12% in jungle (weight=1, total=63)\n" +
                    "# 在沙漠神殿箱子中找到猫咪拖鞋的实际概率（8次抽取）(0.0 - 1.0)\n" +
                    "# 默认 0.20 = 沙漠20%（权重2，总权重69），丛林~12%（权重1，总权重63）\n" +
                    "kittySlippersTempleChestProbability=" + kittySlippersTempleChestProbability + '\n' +
                    "\n" +
                    "# fierceKittySlippersDungeonChestProbability:\n" +
                    "# The ESTIMATED probability of finding Fierce Kitty Slippers in dungeon (8 draws). (0.0 - 1.0)\n" +
                    "# Default 0.15 = ~15% estimated (weight=2, exact depends on dungeon loot table)\n" +
                    "# 在主世界地牢刷怪笼箱子中找到耄耋拖鞋的估计概率（8次抽取）(0.0 - 1.0)\n" +
                    "# 默认 0.15 = ~15% 估计值（权重2，具体取决于地牢战利品表）\n" +
                    "fierceKittySlippersDungeonChestProbability=" + fierceKittySlippersDungeonChestProbability + '\n' +
                    "\n" +
                    "# pocketPistonBlacksmithChestProbability:\n" +
                    "# The probability of finding Pocket Piston in village blacksmith chests. (0.0 - 1.0)\n" +
                    "# 在村庄铁匠铺箱子中找到袖珍活塞的概率 (0.0 - 1.0)\n" +
                    "pocketPistonBlacksmithChestProbability=" + pocketPistonBlacksmithChestProbability + '\n' +
                    "\n" +
                    "# lavaCharmFortressChestProbability:\n" +
                    "# The probability of finding Lava Charm in nether fortress chests. (0.0 - 1.0)\n" +
                    "# 在下界要塞箱子中找到熔岩护身符的概率 (0.0 - 1.0)\n" +
                    "lavaCharmFortressChestProbability=" + lavaCharmFortressChestProbability + '\n' +
                    "\n" +
                    "# bottledCloudDungeonChestProbability:\n" +
                    "# The probability of finding Bottled Cloud in dungeon chests. (0.0 - 1.0)\n" +
                    "# 在地牢箱子中找到云朵瓶的概率 (0.0 - 1.0)\n" +
                    "bottledCloudDungeonChestProbability=" + bottledCloudDungeonChestProbability + '\n' +
                    "\n" +
                    "# regenRingSwampHutChestProbability:\n" +
                    "# The probability of finding Regeneration Ring in swamp hut chests. (0.0 - 1.0)\n" +
                    "# 在女巫小屋箱子中找到再生戒指的概率 (0.0 - 1.0)\n" +
                    "regenRingSwampHutChestProbability=" + regenRingSwampHutChestProbability + '\n' +
                    "\n" +
                    "# waterWalkerFishingProbability:\n" +
                    "# The probability of fishing up Water Walking Boots. (0.0 - 1.0)\n" +
                    "# 钓鱼时获得水上漂靴的概率 (0.0 - 1.0)\n" +
                    "waterWalkerFishingProbability=" + waterWalkerFishingProbability + '\n';

            Files.write(configFile.toPath(), sb.getBytes());
            lastModified = configFile.lastModified();

        } catch (IOException e) {
            System.err.println("[UBConfig] Failed to write config: " + e.getMessage());
        }
    }

    private static double parseDouble(String s, double fallback) {
        if (s == null) return fallback;
        try { return Double.parseDouble(s.trim()); }
        catch (NumberFormatException e) { return fallback; }
    }

    private static int parseInt(String s, int fallback) {
        if (s == null) return fallback;
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException e) { return fallback; }
    }

    private static void reloadIfNeeded() {
        if (configFile != null && configFile.exists()) {
            long m = configFile.lastModified();
            if (m != lastModified) {
                load(configFile);
            }
        }
    }

    public static double getLuckyCloverDropProbability() {
        reloadIfNeeded();
        if (luckyCloverDropProbability < 0.0) luckyCloverDropProbability = 0.0;
        if (luckyCloverDropProbability > 1.0) luckyCloverDropProbability = 1.0;
        return luckyCloverDropProbability;
    }

    public static double getSpiderBezoarDropProbability() {
        reloadIfNeeded();
        if (spiderBezoarDropProbability < 0.0) spiderBezoarDropProbability = 0.0;
        if (spiderBezoarDropProbability > 1.0) spiderBezoarDropProbability = 1.0;
        return spiderBezoarDropProbability;
    }

    public static double getFireElementalLavaCharmDropProbability() {
        reloadIfNeeded();
        if (fireElementalLavaCharmDropProbability < 0.0) fireElementalLavaCharmDropProbability = 0.0;
        if (fireElementalLavaCharmDropProbability > 1.0) fireElementalLavaCharmDropProbability = 1.0;
        return fireElementalLavaCharmDropProbability;
    }

    public static double getWitchRegenRingDropProbability() {
        reloadIfNeeded();
        if (witchRegenRingDropProbability < 0.0) witchRegenRingDropProbability = 0.0;
        if (witchRegenRingDropProbability > 1.0) witchRegenRingDropProbability = 1.0;
        return witchRegenRingDropProbability;
    }

    public static double getCowEternalSteakDropProbability() {
        reloadIfNeeded();
        if (cowEternalSteakDropProbability < 0.0) cowEternalSteakDropProbability = 0.0;
        if (cowEternalSteakDropProbability > 1.0) cowEternalSteakDropProbability = 1.0;
        return cowEternalSteakDropProbability;
    }

    public static int getEternalSteakCooldownTicks() {
        reloadIfNeeded();
        if (eternalSteakCooldownTicks < 0) eternalSteakCooldownTicks = 0;
        return eternalSteakCooldownTicks;
    }

    public static double getMuleLuckyHorseshoeDropProbability() {
        reloadIfNeeded();
        if (muleLuckyHorseshoeDropProbability < 0.0) muleLuckyHorseshoeDropProbability = 0.0;
        if (muleLuckyHorseshoeDropProbability > 1.0) muleLuckyHorseshoeDropProbability = 1.0;
        return muleLuckyHorseshoeDropProbability;
    }

    public static double getKittySlippersTempleChestProbability() {
        reloadIfNeeded();
        if (kittySlippersTempleChestProbability < 0.0) kittySlippersTempleChestProbability = 0.0;
        if (kittySlippersTempleChestProbability > 1.0) kittySlippersTempleChestProbability = 1.0;
        return kittySlippersTempleChestProbability;
    }

    public static double getFierceKittySlippersDungeonChestProbability() {
        reloadIfNeeded();
        if (fierceKittySlippersDungeonChestProbability < 0.0) fierceKittySlippersDungeonChestProbability = 0.0;
        if (fierceKittySlippersDungeonChestProbability > 1.0) fierceKittySlippersDungeonChestProbability = 1.0;
        return fierceKittySlippersDungeonChestProbability;
    }

    public static double getPocketPistonBlacksmithChestProbability() {
        reloadIfNeeded();
        if (pocketPistonBlacksmithChestProbability < 0.0) pocketPistonBlacksmithChestProbability = 0.0;
        if (pocketPistonBlacksmithChestProbability > 1.0) pocketPistonBlacksmithChestProbability = 1.0;
        return pocketPistonBlacksmithChestProbability;
    }

    public static double getLavaCharmFortressChestProbability() {
        reloadIfNeeded();
        if (lavaCharmFortressChestProbability < 0.0) lavaCharmFortressChestProbability = 0.0;
        if (lavaCharmFortressChestProbability > 1.0) lavaCharmFortressChestProbability = 1.0;
        return lavaCharmFortressChestProbability;
    }

    public static double getBottledCloudDungeonChestProbability() {
        reloadIfNeeded();
        if (bottledCloudDungeonChestProbability < 0.0) bottledCloudDungeonChestProbability = 0.0;
        if (bottledCloudDungeonChestProbability > 1.0) bottledCloudDungeonChestProbability = 1.0;
        return bottledCloudDungeonChestProbability;
    }

    public static double getRegenRingSwampHutChestProbability() {
        reloadIfNeeded();
        if (regenRingSwampHutChestProbability < 0.0) regenRingSwampHutChestProbability = 0.0;
        if (regenRingSwampHutChestProbability > 1.0) regenRingSwampHutChestProbability = 1.0;
        return regenRingSwampHutChestProbability;
    }

    public static double getWaterWalkerFishingProbability() {
        reloadIfNeeded();
        if (waterWalkerFishingProbability < 0.0) waterWalkerFishingProbability = 0.0;
        if (waterWalkerFishingProbability > 1.0) waterWalkerFishingProbability = 1.0;
        return waterWalkerFishingProbability;
    }
}

