package com.inf1nlty.uncannybaubles.feature.kittyslippers;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityCreeper;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

public final class KittySlippersEffect {

    public static void playKittySoundOnDamage(EntityPlayer player, EntityDamageResult result) {
        if (wasDamaged(result) && hasKittySlippers(player)) {
            player.worldObj.playSoundAtEntity(
                player,
                "mob.cat.meow",
                1.0F,
                randomPitch(player)
            );
        }
    }

    public static void playFierceKittySoundOnDamage(EntityPlayer player, EntityDamageResult result) {
        if (wasDamaged(result) && hasFierceKittySlippers(player)) {
            player.worldObj.playSoundAtEntity(
                player,
                UBSounds.fierce_kitty_slippers.toString(),
                1.0F,
                randomPitch(player)
            );
        }
    }

    public static boolean shouldPreventCreeperSwell(EntityCreeper creeper) {
        return getKittyTarget(creeper) != null;
    }

    public static void resetCreeperSwell(EntityCreeper creeper) {
        EntityPlayer player = getKittyTarget(creeper);
        if (player != null && creeper.getCreeperState() > 0) {
            creeper.setCreeperState(-1);
        }
    }

    public static boolean shouldPreventCreeperTarget(EntityCreeper creeper) {
        EntityPlayer nearestPlayer = creeper.worldObj.getClosestPlayerToEntity(creeper, 16.0, false);
        return nearestPlayer != null && hasKittySlippers(nearestPlayer);
    }

    public static boolean hasKittySlippers(EntityPlayer player) {
        return BaubleSlotHelper.hasFeetOfType(player, UBItems.kitty_slippers);
    }

    public static boolean hasFierceKittySlippers(EntityPlayer player) {
        return BaubleSlotHelper.hasFeetOfType(player, UBItems.fierce_kitty_slippers);
    }

    private static boolean wasDamaged(EntityDamageResult result) {
        return result != null && result.entityWasNegativelyAffected();
    }

    private static float randomPitch(EntityPlayer player) {
        return (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F + 1.0F;
    }

    private static EntityPlayer getKittyTarget(EntityCreeper creeper) {
        EntityLivingBase target = creeper.getAttackTarget();
        if (!(target instanceof EntityPlayer player)) return null;

        return hasKittySlippers(player) ? player : null;
    }
}
