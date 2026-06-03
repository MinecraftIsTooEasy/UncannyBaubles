package com.inf1nlty.uncannybaubles.client;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
import net.minecraft.EntityPlayer;
import net.minecraft.FontRenderer;
import net.minecraft.Minecraft;
import net.minecraft.ScaledResolution;
import net.minecraft.Translator;

public final class FierceKittySlippersHudRenderer {

    public static void render(Minecraft mc, boolean hasScreen) {
        if (hasScreen) return;

        EntityPlayer player = mc.thePlayer;
        if (player == null) return;
        if (!KittySlippersEffect.hasFierceKittySlippers(player)) return;

        int cooldownTicks = IFierceKittySlippersCooldown.get(player);
        if (cooldownTicks <= 0) return;

        String cooldownText = getCooldownText(cooldownTicks);
        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        FontRenderer fontRenderer = mc.fontRenderer;

        int x = (sr.getScaledWidth() - fontRenderer.getStringWidth(cooldownText)) / 2;
        int y = sr.getScaledHeight() - 79;
        fontRenderer.drawString(cooldownText, x, y, 0xFFFFFF);
    }

    private static String getCooldownText(int cooldownTicks) {
        int cooldownSeconds = cooldownTicks / 20;
        int minutes = cooldownSeconds / 60;
        int seconds = cooldownSeconds % 60;
        return Translator.getFormatted("uncannybaubles.fierce_kitty_slippers.cooldown", minutes, seconds);
    }
}
