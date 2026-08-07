package com.inf1nlty.uncannybaubles.mixin.onionring;

import com.inf1nlty.uncannybaubles.feature.onionring.OnionRingEffect;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public abstract class OnionRingFoodStatsMixin {

    @Shadow private int nutrition;

    @Shadow private EntityPlayer player;

    @Unique private int ub$nutritionBeforeFood;

    @Inject(method = "addFoodValue", at = @At("HEAD"))
    private void ub$recordNutritionBeforeFood(Item item, CallbackInfo ci) {
        this.ub$nutritionBeforeFood = this.nutrition;
    }

    @Inject(method = "addFoodValue", at = @At("RETURN"))
    private void ub$applyOnionRingHaste(Item item, CallbackInfo ci) {
        OnionRingEffect.applyAfterEating(this.player, this.nutrition - this.ub$nutritionBeforeFood);
    }
}
