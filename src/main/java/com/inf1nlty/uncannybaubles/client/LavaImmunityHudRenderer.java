package com.inf1nlty.uncannybaubles.client;

import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.feature.lavaimmunity.LavaImmunityUtil;
import net.minecraft.EntityPlayer;
import net.minecraft.Gui;
import net.minecraft.Minecraft;
import net.minecraft.ScaledResolution;
import org.lwjgl.opengl.GL11;

public final class LavaImmunityHudRenderer extends Gui {

    private static final LavaImmunityHudRenderer INSTANCE = new LavaImmunityHudRenderer();
    private static final int TOTAL_BUBBLES = 7;
    private static final int BUBBLE_U = 16;
    private static final int BUBBLE_V = 18;
    private static final int BUBBLE_SIZE = 9;

    private LavaImmunityHudRenderer() {}

    public static void render(Minecraft mc, boolean hasScreen) {
        if (hasScreen) return;
        if (mc.thePlayer == null) return;
        INSTANCE.renderBubbles(mc, mc.thePlayer);
    }

    private void renderBubbles(Minecraft mc, EntityPlayer player) {
        if (!LavaImmunityUtil.hasLavaImmunityBauble(player)) return;

        int ticks = ILavaImmunity.getTicks(player);
        if (ticks <= 0 || ticks >= LavaImmunityUtil.MAX_TICKS) return;

        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int bubbleY = sr.getScaledHeight() - 59;
        int rightAlign = sr.getScaledWidth() / 2 + 91;
        int fullBubbles = (int) Math.ceil((ticks / (float) LavaImmunityUtil.MAX_TICKS) * TOTAL_BUBBLES);

        mc.getTextureManager().bindTexture(Gui.icons);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);

        for (int i = 0; i < fullBubbles; i++) {
            int bubbleX = rightAlign - (i + 1) * 8 - 1;
            this.drawTexturedModalRect(bubbleX, bubbleY, BUBBLE_U, BUBBLE_V, BUBBLE_SIZE, BUBBLE_SIZE);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
