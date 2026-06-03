package com.inf1nlty.uncannybaubles.feature.brokenankh;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.network.UBNetwork;
import net.minecraft.Damage;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityState;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.ServerPlayer;

public final class BrokenAnkhEffect {

    private static final int EFFECT_DURATION = 11 * 20;
    private static final int COOLDOWN_TICKS = 36000;

    public static boolean tryProtect(EntityPlayer player, Damage damage) {
        if (player.worldObj.isRemote) return false;
        if (player.getHealth() - damage.getAmount() > 0.0F) return false;
        if (!BaubleSlotHelper.hasAmuletOfType(player, UBItems.broken_ankh)) return false;

        if (IBrokenAnkhCooldown.get(player) > 0) return false;

        revive(player);
        IBrokenAnkhCooldown.set(player, COOLDOWN_TICKS);
        return true;
    }

    private static void revive(EntityPlayer player) {
        player.setHealth(1.0F);
        player.hurtTime = player.maxHurtTime = 10;
        player.hurtResistantTime = 40;
        player.limbSwingAmount = 1.5F;

        player.clearActivePotions();
        player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, EFFECT_DURATION, 3));
        player.addPotionEffect(new PotionEffect(Potion.regeneration.id, EFFECT_DURATION, 2));
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, EFFECT_DURATION, 2));

        player.worldObj.setEntityState(player, EnumEntityState.hurt_with_red_tint_refreshed);
        player.worldObj.playSoundAtEntity(player, UBSounds.broken_ankh_totem.toString(), 1.0F, 1.0F);

        if (player instanceof ServerPlayer serverPlayer) {
            UBNetwork.spawnTotemParticles(serverPlayer, player.posX, player.posY, player.posZ);
        }
    }
}
