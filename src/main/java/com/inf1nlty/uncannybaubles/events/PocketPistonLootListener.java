package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class PocketPistonLootListener implements ILootTableRegisterListener {

    @Override
    public void onBlackSmithRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.pocketPistonBlacksmithChestProbability.getDoubleValue();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.pocket_piston, 1),
                1,
                1,
                5
            ));
        }
    }
}