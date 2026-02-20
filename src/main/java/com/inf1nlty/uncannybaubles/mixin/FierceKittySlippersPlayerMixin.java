package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class FierceKittySlippersPlayerMixin {

    @Inject(method = "attackEntityFrom", at = @At("RETURN"))
    private void ub$playFierceKittySoundOnDamage(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        EntityPlayer player = (EntityPlayer) (Object) this;

        if (cir.getReturnValue() != null && cir.getReturnValue().entityWasNegativelyAffected()) {
            if (BaubleSlotHelper.hasFeetOfType(player, UBItems.fierce_kitty_slippers)) {

                player.worldObj.playSoundAtEntity(
                    player,
                    UBSounds.fierce_kitty_slippers.toString(),
                    1.0F,
                    (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F + 1.0F
                );
            }
        }
    }
}