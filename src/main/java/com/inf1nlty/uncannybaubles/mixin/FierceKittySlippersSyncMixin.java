package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import net.minecraft.EntityPlayer;
import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;
import net.minecraft.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

@Mixin(NetClientHandler.class)
public abstract class FierceKittySlippersSyncMixin {

    @Shadow
    private Minecraft mc;

    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    private void ub$handleFierceCooldownSync(Packet250CustomPayload packet, CallbackInfo ci) throws Exception {
        if (packet == null || this.mc.thePlayer == null) return;

        if ("UB|FierceCD".equals(packet.channel)) {
            EntityPlayer player = this.mc.thePlayer;
            if (player instanceof IFierceKittySlippersCooldown fierceCooldown) {
                ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
                DataInputStream dis = new DataInputStream(bis);
                fierceCooldown.ub$setFierceKittySlippersCooldown(dis.readInt());
            }
        }
    }
}