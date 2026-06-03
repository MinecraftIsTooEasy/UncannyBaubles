package com.inf1nlty.uncannybaubles.mixin.bottledcloud;

import com.inf1nlty.uncannybaubles.feature.jump.CloudDoubleJumpEffect;
import net.minecraft.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class BottledCloudEntityLivingBaseMixin {

    @Shadow protected abstract void jump();

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void ub$tryCloudDoubleJump(CallbackInfo ci) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;
        CloudDoubleJumpEffect.onLivingUpdateHead(entity);
        if (CloudDoubleJumpEffect.shouldDoubleJump(entity)) {
            this.jump();
            CloudDoubleJumpEffect.onDoubleJumped(entity);
        }
    }

    @Inject(method = "updateFallState", at = @At("HEAD"))
    private void ub$refreshDoubleJump(double par1, boolean par3, CallbackInfo ci) {
        CloudDoubleJumpEffect.onUpdateFallStateHead((EntityLivingBase) (Object) this, par3);
    }

    @ModifyVariable(method = "fall", at = @At("HEAD"), argsOnly = true)
    private float ub$reduceFallDistance(float distance) {
        return CloudDoubleJumpEffect.modifyFallDistance((EntityLivingBase) (Object) this, distance);
    }

}
