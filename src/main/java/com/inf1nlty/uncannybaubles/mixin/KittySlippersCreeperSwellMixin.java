package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAICreeperSwell.class)
public abstract class KittySlippersCreeperSwellMixin {

    @Shadow EntityCreeper swellingCreeper;

    @Inject(method = "shouldExecute", at = @At("HEAD"), cancellable = true)
    private void ub$preventSwellForKittySlippersPlayer(CallbackInfoReturnable<Boolean> cir) {
        if (this.swellingCreeper == null) {
            return;
        }

        EntityLivingBase target = this.swellingCreeper.getAttackTarget();

        if (target instanceof EntityPlayer player) {

            if (BaublesUtil.hasBaubleWorn(player, UBItems.kitty_slippers)) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "updateTask", at = @At("HEAD"))
    private void ub$resetSwellForKittySlippersPlayer(CallbackInfo ci) {

        if (this.swellingCreeper == null) {
            return;
        }

        EntityLivingBase target = this.swellingCreeper.getAttackTarget();

        if (target instanceof EntityPlayer player) {

            if (BaublesUtil.hasBaubleWorn(player, UBItems.kitty_slippers)) {
                if (this.swellingCreeper.getCreeperState() > 0) {
                    this.swellingCreeper.setCreeperState(-1);
                }
            }
        }
    }
}