package com.inf1nlty.uncannybaubles.mixin.kittyslippers;

import com.inf1nlty.uncannybaubles.client.FierceKittySlippersHudRenderer;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class FierceKittySlippersHudMixin extends Gui {

    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void ub$renderFierceKittySlippersCooldown(float partialTicks, boolean hasScreen, int mouseX, int mouseY, CallbackInfo ci) {
        FierceKittySlippersHudRenderer.render(this.mc, hasScreen);
    }
}
