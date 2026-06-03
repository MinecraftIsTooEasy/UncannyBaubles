package com.inf1nlty.uncannybaubles.feature.eternalbeef;

import com.inf1nlty.uncannybaubles.item.ItemEternalCookedSteak;
import com.inf1nlty.uncannybaubles.item.ItemEternalSteak;
import net.minecraft.Item;
import net.minecraft.ItemStack;

public final class EternalBeefEffect {

    public static boolean shouldBlockUse(ItemStack heldItem, int cooldownTicks) {
        if (cooldownTicks <= 0 || heldItem == null || heldItem.getItem() == null) {
            return false;
        }

        Item item = heldItem.getItem();
        return item instanceof ItemEternalSteak || item instanceof ItemEternalCookedSteak;
    }
}
