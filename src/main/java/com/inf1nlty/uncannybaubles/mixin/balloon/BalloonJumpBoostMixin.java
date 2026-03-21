package com.inf1nlty.uncannybaubles.mixin.balloon;

import com.inf1nlty.uncannybaubles.effect.jump.BalloonJumpBoostEffect;
import net.minecraft.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class BalloonJumpBoostMixin {

    @Inject(method = "jump", at = @At("TAIL"))
    private void ub$balloonJumpBoost(CallbackInfo ci) {
        BalloonJumpBoostEffect.onJump((EntityLivingBase) (Object) this);
    }
}
