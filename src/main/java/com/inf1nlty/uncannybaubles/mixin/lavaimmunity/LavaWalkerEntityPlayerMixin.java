package com.inf1nlty.uncannybaubles.mixin.lavaimmunity;

import com.inf1nlty.uncannybaubles.feature.liquidwalking.LiquidWalkingUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class LavaWalkerEntityPlayerMixin {

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void ub$lavaWalk(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        LiquidWalkingUtil.applyLavaWalker(player);
    }
}
