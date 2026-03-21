package com.inf1nlty.uncannybaubles.mixin.bottledcloud;

import com.inf1nlty.uncannybaubles.effect.jump.CloudDoubleJumpEffect;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.EntityLivingBase;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class BottledCloudEntityLivingBaseMixin {

    @Shadow protected boolean isJumping;

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void ub$captureJumpPressEdge(CallbackInfo ci) {
        CloudDoubleJumpEffect.onLivingUpdateHead((EntityLivingBase) (Object) this, this.isJumping);
    }

    @Inject(method = "updateFallState", at = @At("HEAD"))
    private void ub$refreshDoubleJump(double par1, boolean par3, CallbackInfo ci) {
        CloudDoubleJumpEffect.onUpdateFallStateHead((EntityLivingBase) (Object) this, par3);
    }

    @ModifyVariable(method = "fall", at = @At("HEAD"), argsOnly = true)
    private float ub$reduceFallDistance(float distance) {
        return CloudDoubleJumpEffect.modifyFallDistance((EntityLivingBase) (Object) this, distance);
    }

    @ModifyExpressionValue(method = "onLivingUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;onGround:Z", opcode = Opcodes.GETFIELD))
    private boolean ub$forceJump(boolean original) {
        return CloudDoubleJumpEffect.modifyOnGroundRead((EntityLivingBase) (Object) this, original);
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;jump()V"))
    private void ub$markDoubleJump(CallbackInfo ci) {
        CloudDoubleJumpEffect.onJumpInvoked((EntityLivingBase) (Object) this);
    }
}
