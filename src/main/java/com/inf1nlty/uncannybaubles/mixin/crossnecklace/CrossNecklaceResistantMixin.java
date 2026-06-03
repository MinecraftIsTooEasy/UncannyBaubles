package com.inf1nlty.uncannybaubles.mixin.crossnecklace;

import com.inf1nlty.uncannybaubles.feature.crossnecklace.CrossNecklaceEffect;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class CrossNecklaceResistantMixin extends Entity {

    public CrossNecklaceResistantMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public int maxHurtResistantTime;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$onUpdate(CallbackInfo ci) {
        this.maxHurtResistantTime = CrossNecklaceEffect.getHurtResistantTime(this, this.maxHurtResistantTime);
    }
}
