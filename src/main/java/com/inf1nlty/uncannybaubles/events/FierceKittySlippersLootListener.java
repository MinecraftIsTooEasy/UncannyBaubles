package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfig;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class FierceKittySlippersLootListener implements ILootTableRegisterListener {

    @Override
    public void onDungeonOverworldRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfig.getFierceKittySlippersDungeonChestProbability();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.fierce_kitty_slippers, 1),
                1,
                1,
                2
            ));
        }
    }
}