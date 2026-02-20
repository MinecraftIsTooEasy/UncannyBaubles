package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.Gui;
import net.minecraft.GuiIngame;
import net.minecraft.Minecraft;
import net.minecraft.ScaledResolution;
import org.lwjgl.opengl.GL11;
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

        if (this.mc == null || this.mc.thePlayer == null) return;
        if (hasScreen) return;

        EntityPlayer player = this.mc.thePlayer;

        boolean hasLavaCharm = UBItems.lava_charm != null && BaubleSlotHelper.hasCharmOfType(player, UBItems.lava_charm);
        boolean hasLavaBoots = UBItems.lava_walking_boots != null && BaubleSlotHelper.hasFeetOfType(player, UBItems.lava_walking_boots);

        if (!hasLavaCharm && !hasLavaBoots) return;

        ILavaImmunity immunity = (ILavaImmunity) player;
        int ticks = immunity.ub$getLavaImmunityTicks();

        if (ticks >= 140) return;

        if (ticks <= 0) return;

        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        int totalBubbles = 7;
        int fullBubbles = (int) Math.ceil((ticks / 140.0f) * totalBubbles);

        int bubbleY = screenHeight - 59;
        int rightAlign = screenWidth / 2 + 91;

        this.mc.getTextureManager().bindTexture(Gui.icons);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0f);

        for (int i = 0; i < fullBubbles; i++) {
            int bubbleX = rightAlign - (i + 1) * 8 - 1;

            int u = 16;
            int v = 18;

            this.drawTexturedModalRect(bubbleX, bubbleY, u, v, 9, 9);
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(GL11.GL_BLEND);
    }
}