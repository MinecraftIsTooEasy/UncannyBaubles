package com.inf1nlty.uncannybaubles.mixin.hermesboots;

import com.inf1nlty.uncannybaubles.feature.hermesboots.HermesBootsEffect;
import net.minecraft.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class HermesBootsPlayerCapabilitiesMixin {

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void ub$hermesUpdate(CallbackInfo ci) {
        HermesBootsEffect.update((EntityLivingBase) (Object) this);
    }

    @Redirect(method = "moveEntityWithHeading", at = @At(value  = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;getAIMoveSpeed()F"))
    private float ub$hermesBoostAIMoveSpeed(EntityLivingBase instance)
    {
        return HermesBootsEffect.modifyMoveSpeed(instance, instance.getAIMoveSpeed());
    }
}
