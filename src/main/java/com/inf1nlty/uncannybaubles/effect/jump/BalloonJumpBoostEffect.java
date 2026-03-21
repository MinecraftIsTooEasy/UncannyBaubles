package com.inf1nlty.uncannybaubles.effect.jump;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;

public final class BalloonJumpBoostEffect {

    private static final int TARGET_JUMP_AMPLIFIER = 1; // Jump Boost II

    public static void onJump(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return;
        if (!BalloonEffectHelper.hasJumpBoostBalloon(player)) return;

        int currentAmplifier = getJumpAmplifier(player);
        if (currentAmplifier >= TARGET_JUMP_AMPLIFIER) return;

        double extraMotionY = (TARGET_JUMP_AMPLIFIER - currentAmplifier) * 0.1D;
        entity.motionY += extraMotionY;
    }

    private static int getJumpAmplifier(EntityPlayer player) {
        if (!player.isPotionActive(Potion.jump)) {
            return -1;
        }

        PotionEffect effect = player.getActivePotionEffect(Potion.jump);
        if (effect == null) {
            return -1;
        }

        return effect.getAmplifier();
    }
}
