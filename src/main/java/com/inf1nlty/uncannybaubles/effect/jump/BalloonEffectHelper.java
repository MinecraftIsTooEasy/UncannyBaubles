package com.inf1nlty.uncannybaubles.effect.jump;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityPlayer;

public final class BalloonEffectHelper {

    public static boolean hasJumpBoostBalloon(EntityPlayer player) {
        if (player == null) return false;

        return hasShinyRedBalloon(player) || hasCloudInABalloon(player) || hasBlueHorseshoeBalloon(player);
    }

    public static boolean hasCloudDoubleJump(EntityPlayer player) {
        if (player == null) return false;

        boolean hasBottledCloud = UBItems.bottled_cloud != null && BaubleSlotHelper.hasBeltOfType(player, UBItems.bottled_cloud);
        return hasBottledCloud || hasCloudInABalloon(player) || hasBlueHorseshoeBalloon(player);
    }

    public static boolean hasFallDamageImmunity(EntityPlayer player) {
        if (player == null) return false;

        boolean hasLuckyHorseshoe = UBItems.lucky_horseshoe != null && BaubleSlotHelper.hasFeetOfType(player, UBItems.lucky_horseshoe);
        return hasLuckyHorseshoe || hasBlueHorseshoeBalloon(player);
    }

    private static boolean hasShinyRedBalloon(EntityPlayer player) {
        return UBItems.shiny_red_balloon != null && BaubleSlotHelper.hasBeltOfType(player, UBItems.shiny_red_balloon);
    }

    private static boolean hasCloudInABalloon(EntityPlayer player) {
        return UBItems.cloud_in_a_balloon != null && BaubleSlotHelper.hasBeltOfType(player, UBItems.cloud_in_a_balloon);
    }

    private static boolean hasBlueHorseshoeBalloon(EntityPlayer player) {
        return UBItems.blue_horseshoe_balloon != null && BaubleSlotHelper.hasBeltOfType(player, UBItems.blue_horseshoe_balloon);
    }
}
