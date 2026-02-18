package com.inf1nlty.uncannybaubles.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class LuckyCloverEnchantmentHelperMixin {

    @ModifyReturnValue(method = "getFortuneModifier", at = @At("RETURN"))
    private static int moreFortune(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity instanceof EntityPlayer player && BaublesUtil.hasBaubleWorn(player, UBItems.lucky_clover)) {
            original += 1;
        }
        return original;
    }

    @ModifyReturnValue(method = "getLootingModifier", at = @At("RETURN"))
    private static int moreLoot(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity instanceof EntityPlayer player && BaublesUtil.hasBaubleWorn(player, UBItems.lucky_clover)) {
            original += 1;
        }
        return original;
    }

    @ModifyReturnValue(method = "getButcheringModifier", at = @At("RETURN"))
    private static int moreButcher(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity instanceof EntityPlayer player && BaublesUtil.hasBaubleWorn(player, UBItems.lucky_clover)) {
            original += 1;
        }
        return original;
    }
}