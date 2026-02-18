package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiIngame;
import net.minecraft.I18n;
import net.minecraft.Minecraft;
import net.minecraft.ScaledResolution;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class LavaImmunityHudMixin {

    @Shadow @Final private Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void ub$renderLavaImmunityTimer(float partialTicks, boolean hasScreen, int mouseX, int mouseY, CallbackInfo ci) {

        if (this.mc == null || this.mc.thePlayer == null) return;
        if (hasScreen) return;

        EntityPlayer player = this.mc.thePlayer;

        boolean hasLavaCharm = UBItems.lava_charm != null && BaublesUtil.hasBaubleWorn(player, UBItems.lava_charm);
        boolean hasLavaBoots = UBItems.lava_walking_boots != null && BaublesUtil.hasBaubleWorn(player, UBItems.lava_walking_boots);

        if (!hasLavaCharm && !hasLavaBoots) return;

        ILavaImmunity immunity = (ILavaImmunity) player;
        int ticks = immunity.ub$getLavaImmunityTicks();

        if (ticks >= 140) return;

        if (ticks <= 0) return;

        float seconds = ticks / 20.0f;

        int color;

        if (seconds > 3.5f) {
            color = 0x00FF00; // Green
        }
        else if (seconds > 1.5f)
        {
            color = 0xFFFF00; // Yellow
        } else {
            color = 0xFF0000; // Red
        }

        String text = I18n.getString("uncannybaubles.lava_immunity") + String.format(": %.1fs", seconds);

        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        int x = (screenWidth - this.mc.fontRenderer.getStringWidth(text)) / 2;
        int y = screenHeight - 69;

        this.mc.fontRenderer.drawString(text, x, y, color);
    }
}