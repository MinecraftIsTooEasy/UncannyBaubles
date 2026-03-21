package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class ShinyRedBalloonLootListener implements ILootTableRegisterListener {

    @Override
    public void onFishingRegister(List<WeightedRandomChestContent> original) {
        double probability = UBConfigs.shinyRedBalloonFishingProbability.getDoubleValue();

        if (probability > 0 && UBItems.shiny_red_balloon != null) {
            original.add(new WeightedRandomChestContent(
                new ItemStack(UBItems.shiny_red_balloon, 1),
                1,
                1,
                1
            ));
        }
    }
}
