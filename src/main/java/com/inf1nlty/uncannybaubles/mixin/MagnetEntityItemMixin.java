package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.IAttractableItem;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Entity;
import net.minecraft.EntityItem;
import net.minecraft.EntityPlayer;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityItem.class)
public abstract class MagnetEntityItemMixin extends Entity implements IAttractableItem {

    public MagnetEntityItemMixin(World par1World) {
        super(par1World);
    }

    @Unique
    private boolean ub$attractedByMagnet;

    @Override
    public void ub$setAttractedByMagnet() {
        this.ub$attractedByMagnet = true;
    }

    @WrapOperation(method = "onCollideWithPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;getFootPosY()D"))
    private double attractItem(EntityPlayer instance, Operation<Double> original) {
        if (this.ub$attractedByMagnet) return this.posY;
        return original.call(instance);
    }
}