package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfig;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class BottledCloudLootListener implements ILootTableRegisterListener {

    @Override
    public void onMineshaftRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfig.getBottledCloudDungeonChestProbability();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                    new ItemStack(UBItems.bottled_cloud, 1),
                    1,
                    1,
                    2
            ));
        }
    }

    @Override
    public void onDungeonOverworldRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfig.getBottledCloudDungeonChestProbability();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.bottled_cloud, 1),
                1,
                1,
                2
            ));
        }
    }

    @Override
    public void onDesertPyramidRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfig.getBottledCloudDungeonChestProbability();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.bottled_cloud, 1),
                1,
                1,
                2
            ));
        }
    }

    @Override
    public void onDungeonUnderworldRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfig.getBottledCloudDungeonChestProbability();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.bottled_cloud, 1),
                1,
                1,
                2
            ));
        }
    }
}