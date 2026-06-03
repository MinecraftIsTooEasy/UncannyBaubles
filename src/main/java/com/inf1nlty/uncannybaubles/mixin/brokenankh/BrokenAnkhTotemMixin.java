package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import com.inf1nlty.uncannybaubles.feature.brokenankh.BrokenAnkhEffect;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class BrokenAnkhTotemMixin {

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void ub$brokenAnkhTotem(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer player)) return;

        if (BrokenAnkhEffect.tryProtect(player, damage)) {
            cir.setReturnValue(null);
        }
    }
}
