package com.inf1nlty.uncannybaubles.mixin.lavaimmunity;

import com.inf1nlty.uncannybaubles.client.LavaImmunityHudRenderer;
import net.minecraft.Gui;
import net.minecraft.GuiIngame;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class LavaImmunityHudMixin extends Gui {

    @Shadow @Final private Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void ub$renderLavaImmunityBubbles(float partialTicks, boolean hasScreen, int mouseX, int mouseY, CallbackInfo ci) {
        LavaImmunityHudRenderer.render(this.mc, hasScreen);
    }
}
