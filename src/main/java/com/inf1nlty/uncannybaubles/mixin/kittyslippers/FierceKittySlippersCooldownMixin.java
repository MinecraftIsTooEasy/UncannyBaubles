package com.inf1nlty.uncannybaubles.mixin.kittyslippers;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.util.PlayerIntState;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class FierceKittySlippersCooldownMixin implements IFierceKittySlippersCooldown {

    @Unique
    private final PlayerIntState ub$fierceKittySlippersCooldown =
        new PlayerIntState("ub_fierce_kitty_slippers_cooldown", EnumPlayerStateType.FIERCE_KITTY_SLIPPERS_COOLDOWN);

    @Override
    public int ub$getFierceKittySlippersCooldown() {
        return this.ub$fierceKittySlippersCooldown.get();
    }

    @Override
    public void ub$setFierceKittySlippersCooldown(int ticks) {
        this.ub$fierceKittySlippersCooldown.setAndSync((EntityPlayer) (Object) this, ticks);
    }

    @Override
    public void ub$setFierceKittySlippersCooldownRaw(int ticks) {
        this.ub$fierceKittySlippersCooldown.setRaw(ticks);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateFierceKittySlippersCooldown(CallbackInfo ci) {
        this.ub$fierceKittySlippersCooldown.tickDown();
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeFierceKittySlippersCooldownToNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$fierceKittySlippersCooldown.write(nbt);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readFierceKittySlippersCooldownFromNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$fierceKittySlippersCooldown.read(nbt);
    }
}
