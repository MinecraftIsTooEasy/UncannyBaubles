package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class KittySlippersLootListener implements ILootTableRegisterListener {

    @Override
    public void onDesertPyramidRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.kittySlippersTempleChestProbability.getDoubleValue();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.kitty_slippers, 1),
                1,
                1,
                2
            ));
        }
    }

    @Override
    public void onJunglePyramidRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.kittySlippersTempleChestProbability.getDoubleValue();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.kitty_slippers, 1),
                1,
                1,
                1
            ));
        }
    }
}