package com.inf1nlty.uncannybaubles.feature.bezoar;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;

public final class BezoarEffect {

    public static boolean shouldCancelPotion(EntityLivingBase entity, PotionEffect effect) {
        return effect != null && effect.getPotion() == Potion.poison && hasBezoar(entity);
    }

    public static void removePoisonIfNeeded(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return;
        if (!hasBezoar(player)) return;

        if (player.isPotionActive(Potion.poison)) {
            player.removePotionEffect(Potion.poison.getId());
        }
    }

    private static boolean hasBezoar(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return false;
        if (player.worldObj.isRemote) return false;
        return BaubleSlotHelper.hasCharmOfType(player, UBItems.bezoar);
    }
}
