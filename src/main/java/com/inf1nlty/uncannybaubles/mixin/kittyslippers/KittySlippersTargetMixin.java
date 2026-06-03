package com.inf1nlty.uncannybaubles.mixin.kittyslippers;

import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAINearestAttackableTarget.class)
public abstract class KittySlippersTargetMixin extends EntityAITarget {

    public KittySlippersTargetMixin(EntityCreature par1EntityCreature, boolean par2) {
        super(par1EntityCreature, par2);
    }

    @Inject(method = "shouldExecute", at = @At("HEAD"), cancellable = true)
    private void ub$preventTargetingKittySlippersPlayer(CallbackInfoReturnable<Boolean> cir) {

        if (!(this.taskOwner instanceof EntityCreeper creeper)) {
            return;
        }

        if (KittySlippersEffect.shouldPreventCreeperTarget(creeper)) {
            cir.setReturnValue(false);
        }
    }
}
