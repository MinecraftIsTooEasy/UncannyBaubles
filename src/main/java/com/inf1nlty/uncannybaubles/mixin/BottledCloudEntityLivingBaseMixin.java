package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import net.minecraft.*;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

@Mixin(EntityLivingBase.class)
public abstract class BottledCloudEntityLivingBaseMixin {

    @Unique
    private boolean isJumpingByCloud = false;
    @Unique
    private boolean hasDoubleJumped = false;

    @Inject(method = "updateFallState", at = @At("HEAD"))
    private void refreshDoubleJump(double par1, boolean par3, CallbackInfo ci) {
        if (par3) {
            this.hasDoubleJumped = false;
        }
    }

    @ModifyExpressionValue(method = "onLivingUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;onGround:Z", opcode = Opcodes.GETFIELD))
    private boolean forceJump(boolean original) {
        if (original) {
            this.isJumpingByCloud = false;
            return true;
        }

        if (!this.canDoubleJump()) {
            return false;
        }

        if (!this.hasDoubleJumped) {
            this.isJumpingByCloud = true;
            return true;
        }

        return false;
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;jump()V"))
    private void mark(CallbackInfo ci) {
        if (this.isJumpingByCloud) {
            this.hasDoubleJumped = true;

            EntityLivingBase self = (EntityLivingBase) (Object) this;
            if (self instanceof EntityPlayer player) {
                player.playSound(UBSounds.double_jump.toString(), 1.0F, 1.0F);
            }
        }
    }

    @Unique
    private boolean canDoubleJump() {
        Entity self = (Entity) (Object) this;
        if (!(self instanceof EntityPlayer player)) return false;
        return BaubleSlotHelper.hasBeltOfType(player, UBItems.bottled_cloud);
    }
}