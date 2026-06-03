package com.inf1nlty.uncannybaubles.mixin.luckyclover;

import com.inf1nlty.uncannybaubles.feature.luckyclover.LuckyCloverExtraDrops;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseExtraDropsMixin {

    @Shadow protected int recentlyHit;

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void ub$extraDrop(DamageSource source, CallbackInfo ci)
    {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        LuckyCloverExtraDrops.applyIfEligible(self, source, recentlyHit);
    }
}
