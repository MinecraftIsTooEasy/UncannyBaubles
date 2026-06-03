package com.inf1nlty.uncannybaubles.mixin.kittyslippers;

import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
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
        if (KittySlippersEffect.shouldPreventCreeperSwell(this.swellingCreeper)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "updateTask", at = @At("HEAD"))
    private void ub$resetSwellForKittySlippersPlayer(CallbackInfo ci) {
        KittySlippersEffect.resetCreeperSwell(this.swellingCreeper);
    }
}
