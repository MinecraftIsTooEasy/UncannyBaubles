package com.inf1nlty.uncannybaubles.mixin.eternalbeef;

import com.inf1nlty.uncannybaubles.api.ICooldown;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@Mixin(ServerPlayer.class)
public abstract class EternalBeefRespawnMixin {

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void ub$copySteakCooldownOnClone(EntityPlayer source, boolean keepInventory, CallbackInfo ci)
    {
        ICooldown src = (ICooldown) source;
        ICooldown dst = (ICooldown) this;
        dst.ub$setEternalBeefCooldownRaw(src.ub$getEternalBeefCooldown());
    }

    @Inject(method = "afterRespawn", at = @At("RETURN"))
    private void ub$resyncSteakCooldownAfterRespawn(CallbackInfo ci)
    {
        ServerPlayer serverPlayer = (ServerPlayer) (Object) this;

        ICooldown cd = (ICooldown) (EntityPlayer) serverPlayer;

        int value = cd.ub$getEternalBeefCooldown();

        if (value > 0)
        {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                new DataOutputStream(bos).writeInt(value);
                serverPlayer.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|SteakCD", bos.toByteArray())
                );
            } catch (Exception ignored) {}
        }
    }
}