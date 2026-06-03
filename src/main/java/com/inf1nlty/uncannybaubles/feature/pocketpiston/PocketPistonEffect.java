package com.inf1nlty.uncannybaubles.feature.pocketpiston;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityPlayer;

public final class PocketPistonEffect {

    private static final float REACH_BONUS = 1.0F;

    public static float applyReachBonus(EntityPlayer player, float original) {
        return hasPocketPiston(player) ? original + REACH_BONUS : original;
    }

    public static boolean hasPocketPiston(EntityPlayer player) {
        return BaubleSlotHelper.hasHandOfType(player, UBItems.pocket_piston);
    }
}
