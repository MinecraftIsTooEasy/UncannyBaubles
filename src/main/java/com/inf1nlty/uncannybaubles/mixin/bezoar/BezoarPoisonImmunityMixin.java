package com.inf1nlty.uncannybaubles.mixin.bezoar;

import com.inf1nlty.uncannybaubles.feature.bezoar.BezoarEffect;
import net.minecraft.EntityLivingBase;
import net.minecraft.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class BezoarPoisonImmunityMixin {

    @Inject(method = "addPotionEffect", at = @At("HEAD"), cancellable = true)
    private void onAddPotionEffect(PotionEffect effect, CallbackInfo ci) {
        if (BezoarEffect.shouldCancelPotion((EntityLivingBase) (Object) this, effect)) {
            ci.cancel();
        }
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onEntityUpdate(CallbackInfo ci) {
        BezoarEffect.removePoisonIfNeeded((EntityLivingBase) (Object) this);
    }
}
