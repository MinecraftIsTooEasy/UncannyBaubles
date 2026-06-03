package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.util.UBPlayerStateSync;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class PlayerStateRespawnMixin {

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void ub$copyPlayerStateOnClone(EntityPlayer source, boolean keepInventory, CallbackInfo ci) {
        UBPlayerStateSync.copyToClone(source, (ServerPlayer) (Object) this);
    }

    @Inject(method = "afterRespawn", at = @At("RETURN"))
    private void ub$resyncPlayerStateAfterRespawn(CallbackInfo ci) {
        UBPlayerStateSync.syncAll((ServerPlayer) (Object) this);
    }
}
