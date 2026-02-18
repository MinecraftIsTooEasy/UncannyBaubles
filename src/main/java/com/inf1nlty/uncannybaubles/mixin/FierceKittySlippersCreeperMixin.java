package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.ai.EntityAIAvoidPlayerWithFierceKittySlippers;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public abstract class FierceKittySlippersCreeperMixin extends EntityMob {

    public FierceKittySlippersCreeperMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void ub$addAvoidFierceKittySlippersAI(World world, CallbackInfo ci) {
        EntityCreeper creeper = (EntityCreeper) (Object) this;

        creeper.tasks.addTask(0, new EntityAIAvoidPlayerWithFierceKittySlippers(creeper, 6.0F, 1.0D, 1.2D));
    }
}