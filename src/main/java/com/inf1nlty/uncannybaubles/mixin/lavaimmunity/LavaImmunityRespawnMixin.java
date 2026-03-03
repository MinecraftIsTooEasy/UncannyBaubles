package com.inf1nlty.uncannybaubles.mixin.lavaimmunity;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.api.ILavaImmunity;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@Mixin(ServerPlayer.class)
public abstract class LavaImmunityRespawnMixin {

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void ub$copyFierceCooldownOnClone(EntityPlayer source, boolean keepInventory, CallbackInfo ci)
    {
        IFierceKittySlippersCooldown src = (IFierceKittySlippersCooldown) source;
        IFierceKittySlippersCooldown dst = (IFierceKittySlippersCooldown) this;
        dst.ub$setFierceKittySlippersCooldown(src.ub$getFierceKittySlippersCooldown());
    }

    @Inject(method = "afterRespawn", at = @At("RETURN"))
    private void ub$resyncAfterRespawn(CallbackInfo ci) {
        ServerPlayer serverPlayer = (ServerPlayer) (Object) this;
        EntityPlayer entityPlayer = serverPlayer;

        try {
            ILavaImmunity immunity = (ILavaImmunity) entityPlayer;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new DataOutputStream(bos).writeInt(immunity.ub$getLavaImmunityTicks());
            serverPlayer.playerNetServerHandler.sendPacketToPlayer(
                new Packet250CustomPayload("UB|LavaImm", bos.toByteArray())
            );
        } catch (Exception ignored) {}

        try {
            IFierceKittySlippersCooldown fierce = (IFierceKittySlippersCooldown) entityPlayer;
            int value = fierce.ub$getFierceKittySlippersCooldown();
            if (value > 0) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                new DataOutputStream(bos).writeInt(value);
                serverPlayer.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|FierceCD", bos.toByteArray())
                );
            }
        } catch (Exception ignored) {}
    }
}