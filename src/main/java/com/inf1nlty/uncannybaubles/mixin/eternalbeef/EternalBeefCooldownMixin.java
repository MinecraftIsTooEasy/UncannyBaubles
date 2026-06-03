package com.inf1nlty.uncannybaubles.mixin.eternalbeef;

import com.inf1nlty.uncannybaubles.api.ICooldown;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.feature.eternalbeef.EternalBeefEffect;
import com.inf1nlty.uncannybaubles.util.PlayerIntState;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EternalBeefCooldownMixin implements ICooldown {

    @Shadow
    public abstract ItemStack getHeldItemStack();

    @Unique
    private final PlayerIntState ub$eternalBeefCooldown =
        new PlayerIntState("ub_eternal_beef_cooldown", EnumPlayerStateType.ETERNAL_BEEF_COOLDOWN);

    @Override
    public int ub$getEternalBeefCooldown() {
        return this.ub$eternalBeefCooldown.get();
    }

    @Override
    public void ub$setEternalBeefCooldown(int ticks) {
        this.ub$eternalBeefCooldown.setAndSync((EntityPlayer) (Object) this, ticks);
    }

    @Override
    public void ub$setEternalBeefCooldownRaw(int ticks) {
        this.ub$eternalBeefCooldown.setRaw(ticks);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateEternalBeefCooldown(CallbackInfo ci) {
        this.ub$eternalBeefCooldown.tickDown();
    }

    @Inject(method = "setHeldItemInUse", at = @At("HEAD"), cancellable = true)
    private void ub$blockEternalSteakDuringCooldown(CallbackInfoReturnable<Boolean> cir) {
        if (EternalBeefEffect.shouldBlockUse(this.getHeldItemStack(), this.ub$eternalBeefCooldown.get())) {
            cir.cancel();
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeCooldownToNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$eternalBeefCooldown.write(nbt);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readCooldownFromNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$eternalBeefCooldown.read(nbt);
    }
}
