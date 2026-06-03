package com.inf1nlty.uncannybaubles.feature.regenbracelet;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;

public final class RegenBraceletEffect {

    private static final float HEAL_PROGRESS_MULTIPLIER = 1.25F;

    public static float getExtraHealProgress(ServerPlayer serverPlayer, EntityPlayer foodStatsPlayer, int nutrition) {
        if (serverPlayer.isGhost() || serverPlayer.isZevimrgvInTournament()) return 0.0F;
        if (serverPlayer.isDead || serverPlayer.getHealth() <= 0.0F) return 0.0F;
        if (foodStatsPlayer.isStarving()) return 0.0F;
        if (!serverPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) return 0.0F;
        if (!serverPlayer.shouldHeal()) return 0.0F;
        if (!BaubleSlotHelper.hasBraceletOfType(serverPlayer, UBItems.regen_bracelet)) return 0.0F;

        float baseIncrement = (4.0E-4F + (float) nutrition * 2.0E-5F)
            * (serverPlayer.isMalnourished() ? 0.25F : 1.0F)
            * (serverPlayer.inBed() ? 4.0F : 1.0F)
            * EnchantmentHelper.getRegenerationModifier(foodStatsPlayer);

        return baseIncrement * (HEAL_PROGRESS_MULTIPLIER - 1.0F);
    }
}
