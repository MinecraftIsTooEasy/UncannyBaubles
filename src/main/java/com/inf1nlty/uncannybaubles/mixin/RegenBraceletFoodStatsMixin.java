package com.inf1nlty.uncannybaubles.mixin;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public abstract class RegenBraceletFoodStatsMixin {

    @Shadow private int nutrition;
    @Shadow private EntityPlayer player;
    @Shadow private float heal_progress;

    @Unique private static final float RING_HEAL_PROGRESS_MULTIPLIER = 1.25F;

    @Inject(method = "onUpdate(Lnet/minecraft/ServerPlayer;)V", at = @At("HEAD"))
    private void ub$onUpdate_head(ServerPlayer par1EntityPlayer, CallbackInfo ci) {

        if (par1EntityPlayer.isGhost() || par1EntityPlayer.isZevimrgvInTournament()) return;

        if (par1EntityPlayer.isDead || par1EntityPlayer.getHealth() <= 0.0F) return;

        if (this.player.isStarving()) return;

        if (!par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) return;

        if (!par1EntityPlayer.shouldHeal()) return;

        float baseIncrement = (4.0E-4F + (float)this.nutrition * 2.0E-5F)
                * (par1EntityPlayer.isMalnourished() ? 0.25F : 1.0F)
                * (par1EntityPlayer.inBed() ? 4.0F : 1.0F)
                * EnchantmentHelper.getRegenerationModifier(this.player);

        if (UBItems.regen_bracelet != null && BaubleSlotHelper.hasBraceletOfType(par1EntityPlayer, UBItems.regen_bracelet)) {
            float extra = baseIncrement * (RING_HEAL_PROGRESS_MULTIPLIER - 1.0f);
            this.heal_progress += extra;
        }
    }
}