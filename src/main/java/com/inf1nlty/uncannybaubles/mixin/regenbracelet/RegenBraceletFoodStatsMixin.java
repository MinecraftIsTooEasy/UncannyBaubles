package com.inf1nlty.uncannybaubles.mixin.regenbracelet;

import com.inf1nlty.uncannybaubles.feature.regenbracelet.RegenBraceletEffect;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public abstract class RegenBraceletFoodStatsMixin {

    @Shadow private int nutrition;

    @Shadow private EntityPlayer player;

    @Shadow private float heal_progress;

    @Inject(method = "onUpdate(Lnet/minecraft/ServerPlayer;)V", at = @At("HEAD"))
    private void ub$onUpdate_head(ServerPlayer par1EntityPlayer, CallbackInfo ci) {
        this.heal_progress += RegenBraceletEffect.getExtraHealProgress(par1EntityPlayer, this.player, this.nutrition);
    }
}
