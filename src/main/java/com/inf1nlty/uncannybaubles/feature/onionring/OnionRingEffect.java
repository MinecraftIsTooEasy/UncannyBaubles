package com.inf1nlty.uncannybaubles.feature.onionring;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;

public final class OnionRingEffect {

    private static final int TICKS_PER_SECOND = 20;
    private static final int HASTE_DURATION_PER_FOOD_POINT = 6 * TICKS_PER_SECOND;
    private static final int DURATION_DISPLAY_PADDING_TICKS = 19;
    private static final int HASTE_II_AMPLIFIER = 1;

    public static void applyAfterEating(EntityPlayer player, int restoredNutrition) {
        if (restoredNutrition <= 0) return;
        if (player.worldObj.isRemote) return;
        if (!BaubleSlotHelper.hasHandOfType(player, UBItems.onion_ring)) return;

        player.addPotionEffect(new PotionEffect(
            Potion.digSpeed.id,
            restoredNutrition * HASTE_DURATION_PER_FOOD_POINT + DURATION_DISPLAY_PADDING_TICKS,
            HASTE_II_AMPLIFIER
        ));
    }

    private OnionRingEffect() {
    }
}
