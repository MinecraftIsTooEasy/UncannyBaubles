package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.ai.EntityAIAvoidPlayerWithKittySlippers;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public abstract class KittySlippersCreeperMixin extends EntityMob {

    public KittySlippersCreeperMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void ub$addAvoidKittySlippersAI(World world, CallbackInfo ci) {

        EntityCreeper creeper = (EntityCreeper) (Object) this;
        creeper.tasks.addTask(1, new EntityAIAvoidPlayerWithKittySlippers(creeper, 6.0F, 1.0D, 1.2D));
    }
}