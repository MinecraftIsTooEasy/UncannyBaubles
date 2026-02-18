package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class BezoarPoisonImmunityMixin {

    @Inject(method = "addPotionEffect", at = @At("HEAD"), cancellable = true)
    private void onAddPotionEffect(PotionEffect effect, CallbackInfo ci) {
        if (effect == null) return;

        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer player)) return;

        if (player.worldObj == null || player.worldObj.isRemote) return;

        if (UBItems.bezoar == null) return;
        if (!BaublesUtil.hasBaubleWorn(player, UBItems.bezoar)) return;

        if (effect.getPotion() == Potion.poison) {
            ci.cancel();
        }
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onEntityUpdate(CallbackInfo ci) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer player)) return;

        if (player.worldObj == null || player.worldObj.isRemote) return;

        if (UBItems.bezoar == null) return;
        if (!BaublesUtil.hasBaubleWorn(player, UBItems.bezoar)) return;

        if (player.isPotionActive(Potion.poison)) {
            int poisonId = Potion.poison.getId();
            player.removePotionEffect(poisonId);
        }
    }
}