package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import com.inf1nlty.uncannybaubles.feature.diggingclaws.DiggingClawsUtil;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class DiggingClawsSpeedMixin {

    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At("RETURN"), cancellable = true)
    private void ub$diggingClawsStoneSpeed(int x, int y, int z, boolean apply_held_item, CallbackInfoReturnable<Float> cir)
    {
        EntityPlayer player = (EntityPlayer) (Object) this;
        float speed = DiggingClawsUtil.getBareHandStoneSpeed(player, x, y, z, cir.getReturnValue());
        if (speed != cir.getReturnValue()) {
            cir.setReturnValue(speed);
        }
    }
}
