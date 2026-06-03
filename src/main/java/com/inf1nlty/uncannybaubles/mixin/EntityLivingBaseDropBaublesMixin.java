package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.feature.baubledrops.BaubleDropRules;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseDropBaublesMixin {

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void dropLuckyClover(DamageSource source, CallbackInfo ci) {
        BaubleDropRules.apply((EntityLivingBase) (Object) this);
    }
}
