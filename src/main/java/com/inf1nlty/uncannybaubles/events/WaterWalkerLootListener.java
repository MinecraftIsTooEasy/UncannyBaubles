package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class WaterWalkerLootListener implements ILootTableRegisterListener {

    @Override
    public void onFishingRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.waterWalkerFishingProbability.getDoubleValue();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.water_walking_boots, 1),
                1,
                1,
                3
            ));
        }
    }
}