package com.inf1nlty.uncannybaubles.mixin;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.item.UBItems;
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

        if (this.mc == null || this.mc.thePlayer == null) return;
        if (hasScreen) return;

        EntityPlayer player = this.mc.thePlayer;

        if (!BaubleSlotHelper.hasFeetOfType(player, UBItems.fierce_kitty_slippers)) {
            return;
        }

        IFierceKittySlippersCooldown cooldownData = (IFierceKittySlippersCooldown) player;
        int cooldownTicks = cooldownData.ub$getFierceKittySlippersCooldown();

        if (cooldownTicks <= 0) {
            return;
        }

        int cooldownSeconds = cooldownTicks / 20;
        int minutes = cooldownSeconds / 60;
        int seconds = cooldownSeconds % 60;

        String cooldownText = Translator.getFormatted("uncannybaubles.fierce_kitty_slippers.cooldown", minutes, seconds);

        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        FontRenderer fontRenderer = this.mc.fontRenderer;
        int textWidth = fontRenderer.getStringWidth(cooldownText);

        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight - 79;

        fontRenderer.drawString(cooldownText, x, y, 0xFFFFFF);
    }
}