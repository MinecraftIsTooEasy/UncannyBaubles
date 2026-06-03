package com.inf1nlty.uncannybaubles.mixin.lavaimmunity;

import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.feature.lavaimmunity.LavaImmunityUtil;
import com.inf1nlty.uncannybaubles.util.PlayerIntState;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class LavaImmunityMixin implements ILavaImmunity {

    @Unique
    private final PlayerIntState ub$lavaImmunityTicks =
        new PlayerIntState("ub_lava_immunity_ticks", EnumPlayerStateType.LAVA_IMMUNITY, LavaImmunityUtil.MAX_TICKS);

    @Override
    public int ub$getLavaImmunityTicks() {
        return this.ub$lavaImmunityTicks.get();
    }

    @Override
    public void ub$setLavaImmunityTicks(int ticks) {
        this.ub$lavaImmunityTicks.setRaw(ticks);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateLavaImmunity(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        this.ub$lavaImmunityTicks.setRaw(LavaImmunityUtil.updateTicks(player, this.ub$lavaImmunityTicks.get()));
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void ub$preventLavaDamage(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        EntityPlayer player = (EntityPlayer) (Object) this;

        if (LavaImmunityUtil.shouldCancelDamage(this.ub$lavaImmunityTicks.get(), damage)) {
            cir.setReturnValue(null);
            player.extinguish();
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeImmunityToNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$lavaImmunityTicks.write(nbt);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readImmunityFromNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.ub$lavaImmunityTicks.read(nbt);
    }
}
