package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class LuckyHorseshoeFallDamageMixin {

    @Inject(method = "fall", at = @At("HEAD"), cancellable = true)
    private void ub$preventFallDamage(float distance, CallbackInfo ci) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;

        if (!(entity instanceof EntityPlayer player)) return;

        if (BaubleSlotHelper.hasFeetOfType(player, UBItems.lucky_horseshoe))
            ci.cancel();
    }
}