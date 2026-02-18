package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class FierceKittySlippersCooldownMixin implements IFierceKittySlippersCooldown {

    @Unique
    private int ub$fierceKittySlippersCooldown = 0;

    @Override
    public int ub$getFierceKittySlippersCooldown() {
        return this.ub$fierceKittySlippersCooldown;
    }

    @Override
    public void ub$setFierceKittySlippersCooldown(int ticks) {
        this.ub$fierceKittySlippersCooldown = Math.max(0, ticks);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateFierceKittySlippersCooldown(CallbackInfo ci) {
        if (this.ub$fierceKittySlippersCooldown > 0) {
            this.ub$fierceKittySlippersCooldown--;
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeFierceKittySlippersCooldownToNBT(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt != null) {
            nbt.setInteger("ub_fierce_kitty_slippers_cooldown", this.ub$fierceKittySlippersCooldown);
        }
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readFierceKittySlippersCooldownFromNBT(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt != null && nbt.hasKey("ub_fierce_kitty_slippers_cooldown")) {
            this.ub$fierceKittySlippersCooldown = nbt.getInteger("ub_fierce_kitty_slippers_cooldown");
        } else {
            this.ub$fierceKittySlippersCooldown = 0;
        }
    }
}