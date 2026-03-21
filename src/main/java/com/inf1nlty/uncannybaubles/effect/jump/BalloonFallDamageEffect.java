package com.inf1nlty.uncannybaubles.effect.jump;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

public final class BalloonFallDamageEffect {

    public static boolean shouldCancelFallDamage(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return false;
        return BalloonEffectHelper.hasFallDamageImmunity(player);
    }
}
