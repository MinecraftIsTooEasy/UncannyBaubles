package com.inf1nlty.uncannybaubles.mixin.luckyclover;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.inf1nlty.uncannybaubles.feature.luckyclover.LuckyCloverEffect;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class LuckyCloverEnchantmentHelperMixin {

    @ModifyReturnValue(method = "getFortuneModifier", at = @At("RETURN"))
    private static int moreFortune(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        return LuckyCloverEffect.applyEnchantmentBonus(original, entity);
    }

    @ModifyReturnValue(method = "getLootingModifier", at = @At("RETURN"))
    private static int moreLoot(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        return LuckyCloverEffect.applyEnchantmentBonus(original, entity);
    }

    @ModifyReturnValue(method = "getButcheringModifier", at = @At("RETURN"))
    private static int moreButcher(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        return LuckyCloverEffect.applyEnchantmentBonus(original, entity);
    }
}
