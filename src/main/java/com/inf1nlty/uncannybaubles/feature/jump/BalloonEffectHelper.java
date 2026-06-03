package com.inf1nlty.uncannybaubles.feature.jump;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityPlayer;

public final class BalloonEffectHelper {

    public static boolean hasJumpBoostBalloon(EntityPlayer player) {
        return hasShinyRedBalloon(player) || hasCloudInABalloon(player) || hasBlueHorseshoeBalloon(player);
    }

    public static boolean hasCloudDoubleJump(EntityPlayer player) {
        boolean hasBottledCloud = BaubleSlotHelper.hasBeltOfType(player, UBItems.bottled_cloud);
        return hasBottledCloud || hasCloudInABalloon(player) || hasBlueHorseshoeBalloon(player);
    }

    public static boolean hasFallDamageImmunity(EntityPlayer player) {
        boolean hasLuckyHorseshoe = BaubleSlotHelper.hasFeetOfType(player, UBItems.lucky_horseshoe);
        return hasLuckyHorseshoe || hasBlueHorseshoeBalloon(player);
    }

    private static boolean hasShinyRedBalloon(EntityPlayer player) {
        return BaubleSlotHelper.hasBeltOfType(player, UBItems.shiny_red_balloon);
    }

    private static boolean hasCloudInABalloon(EntityPlayer player) {
        return BaubleSlotHelper.hasBeltOfType(player, UBItems.cloud_in_a_balloon);
    }

    private static boolean hasBlueHorseshoeBalloon(EntityPlayer player) {
        return BaubleSlotHelper.hasBeltOfType(player, UBItems.blue_horseshoe_balloon);
    }
}
