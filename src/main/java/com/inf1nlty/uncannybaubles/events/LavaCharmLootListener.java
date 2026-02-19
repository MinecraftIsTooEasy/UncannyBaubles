package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class LavaCharmLootListener implements ILootTableRegisterListener {

    @Override
    public void onFortressRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.lavaCharmFortressChestProbability.getDoubleValue();

        if (probability > 0) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.lava_charm, 1),
                1,
                1,
                3
            ));
        }
    }
}