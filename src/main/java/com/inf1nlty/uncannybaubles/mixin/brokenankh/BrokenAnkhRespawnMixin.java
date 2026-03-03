package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@Mixin(ServerPlayer.class)
public abstract class BrokenAnkhRespawnMixin {

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void ub$copyAnkhCooldownOnClone(EntityPlayer source, boolean keepInventory, CallbackInfo ci)
    {
        IBrokenAnkhCooldown src = (IBrokenAnkhCooldown) source;
        IBrokenAnkhCooldown dst = (IBrokenAnkhCooldown) this;
        dst.ub$setBrokenAnkhCooldownRaw(src.ub$getBrokenAnkhCooldown());
    }

    @Inject(method = "afterRespawn", at = @At("RETURN"))
    private void ub$resyncAnkhCooldownAfterRespawn(CallbackInfo ci)
    {
        ServerPlayer serverPlayer = (ServerPlayer) (Object) this;

        IBrokenAnkhCooldown cd = (IBrokenAnkhCooldown) (EntityPlayer) serverPlayer;

        int value = 0;

        if (cd != null)
        {
            value = cd.ub$getBrokenAnkhCooldown();
        }
        if (value > 0)
        {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                new DataOutputStream(bos).writeInt(value);
                serverPlayer.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|AnkhCD", bos.toByteArray())
                );
            } catch (Exception ignored) {}
        }
    }
}