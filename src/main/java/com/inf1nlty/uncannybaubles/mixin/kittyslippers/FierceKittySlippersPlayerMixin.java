package com.inf1nlty.uncannybaubles.mixin.kittyslippers;

import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class FierceKittySlippersPlayerMixin {

    @Inject(method = "attackEntityFrom", at = @At("RETURN"))
    private void ub$playFierceKittySoundOnDamage(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        KittySlippersEffect.playFierceKittySoundOnDamage((EntityPlayer) (Object) this, cir.getReturnValue());
    }
}
