package com.inf1nlty.uncannybaubles.feature.luckyclover;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

public final class LuckyCloverEffect {

    public static int applyEnchantmentBonus(int original, EntityLivingBase entity) {
        return hasLuckyClover(entity) ? original + 1 : original;
    }

    public static boolean hasLuckyClover(EntityLivingBase entity) {
        return entity instanceof EntityPlayer player && hasLuckyClover(player);
    }

    public static boolean hasLuckyClover(EntityPlayer player) {
        return BaubleSlotHelper.hasHeadOfType(player, UBItems.lucky_clover);
    }
}
