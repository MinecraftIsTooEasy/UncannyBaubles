package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
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

        EntityPlayer nearestPlayer = creeper.worldObj.getClosestPlayerToEntity(creeper, 16.0, false);

        if (BaublesUtil.hasBaubleWorn(nearestPlayer, UBItems.kitty_slippers)) {

            cir.setReturnValue(false);
        }
    }
}