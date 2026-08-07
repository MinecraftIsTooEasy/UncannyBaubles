package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class UBChestLootListener implements ILootTableRegisterListener {

    @Override
    public void onMineshaftRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.onion_ring, UBConfigs.onionRingChestProbability.getDoubleValue(), 1, 1, 2);
        addBottledCloud(original, 2);
    }

    @Override
    public void onDungeonOverworldRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.fierce_kitty_slippers, UBConfigs.fierceKittySlippersDungeonChestProbability.getDoubleValue(), 1, 1, 2);
        addBottledCloud(original, 2);
    }

    @Override
    public void onDesertPyramidRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.kitty_slippers, UBConfigs.kittySlippersTempleChestProbability.getDoubleValue(), 1, 1, 2);
        ChestLootEntries.add(original, UBItems.onion_ring, UBConfigs.onionRingChestProbability.getDoubleValue(), 1, 1, 1);
        addBottledCloud(original, 2);
    }

    @Override
    public void onJunglePyramidRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.kitty_slippers, UBConfigs.kittySlippersTempleChestProbability.getDoubleValue(), 1, 1, 1);
    }

    @Override
    public void onBlackSmithRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.pocket_piston, UBConfigs.pocketPistonBlacksmithChestProbability.getDoubleValue(), 1, 1, 5);
    }

    @Override
    public void onFortressRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.lava_charm, UBConfigs.lavaCharmFortressChestProbability.getDoubleValue(), 1, 1, 3);
    }

    @Override
    public void onDungeonUnderworldRegister(List<WeightedRandomChestContent> original) {
        addBottledCloud(original, 1);
    }

    @Override
    public void onSwampHutRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.regen_bracelet, UBConfigs.regenRingSwampHutChestProbability.getDoubleValue(), 1, 1, 1);
    }

    @Override
    public void onFishingRegister(List<WeightedRandomChestContent> original) {
        ChestLootEntries.add(original, UBItems.water_walking_boots, UBConfigs.waterWalkerFishingProbability.getDoubleValue(), 1, 1, 3);
        ChestLootEntries.add(original, UBItems.shiny_red_balloon, UBConfigs.shinyRedBalloonFishingProbability.getDoubleValue(), 1, 1, 1);
    }

    private static void addBottledCloud(List<WeightedRandomChestContent> original, int weight) {
        ChestLootEntries.add(original, UBItems.bottled_cloud, UBConfigs.bottledCloudDungeonChestProbability.getDoubleValue(), 1, 1, weight);
    }
}
