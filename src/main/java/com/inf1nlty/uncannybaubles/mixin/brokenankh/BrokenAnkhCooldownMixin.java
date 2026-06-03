package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.util.PlayerIntState;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class BrokenAnkhCooldownMixin implements IBrokenAnkhCooldown {

    @Unique
    private final PlayerIntState ub$brokenAnkhCooldown =
        new PlayerIntState("ub_broken_ankh_cooldown", EnumPlayerStateType.BROKEN_ANKH_COOLDOWN);

    @Override
    public int ub$getBrokenAnkhCooldown() {
        return this.ub$brokenAnkhCooldown.get();
    }

    @Override
    public void ub$setBrokenAnkhCooldown(int ticks) {
        this.ub$brokenAnkhCooldown.setAndSync((EntityPlayer) (Object) this, ticks);
    }

    @Override
    public void ub$setBrokenAnkhCooldownRaw(int ticks) {
        this.ub$brokenAnkhCooldown.setRaw(ticks);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$tickBrokenAnkhCooldown(CallbackInfo ci) {
        this.ub$brokenAnkhCooldown.tickDown();
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeAnkhCooldownNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$brokenAnkhCooldown.write(nbt);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readAnkhCooldownNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$brokenAnkhCooldown.read(nbt);
    }
}
