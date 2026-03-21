package com.inf1nlty.uncannybaubles.mixin.luckyhorseshoe;

import com.inf1nlty.uncannybaubles.effect.jump.BalloonFallDamageEffect;
import net.minecraft.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class LuckyHorseshoeFallDamageMixin {

    @Inject(method = "fall", at = @At("HEAD"), cancellable = true)
    private void ub$preventFallDamage(float distance, CallbackInfo ci) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;

        if (BalloonFallDamageEffect.shouldCancelFallDamage(entity)) {
            ci.cancel();
        }
    }
}
