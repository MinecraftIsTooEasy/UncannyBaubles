package com.inf1nlty.uncannybaubles.mixin.waterwalker;

import com.inf1nlty.uncannybaubles.feature.liquidwalking.LiquidWalkingUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class WaterWalkerEntityPlayerMixin {

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void ub$waterWalk(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        LiquidWalkingUtil.applyWaterWalker(player);
    }
}
