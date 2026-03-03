package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import com.inf1nlty.uncannybaubles.client.ParticleHelper;
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
public abstract class BrokenAnkhParticleSyncMixin {

    @Shadow private Minecraft mc;

    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    private void ub$handleAnkhParticle(Packet250CustomPayload packet, CallbackInfo ci) {
        if (packet == null || packet.data == null) return;
        if (!"UB|AnkhFX".equals(packet.channel)) return;

        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.data))) {
            double x = in.readDouble();
            double y = in.readDouble();
            double z = in.readDouble();

            if (this.mc != null && this.mc.theWorld != null) {
                ParticleHelper.spawnTotemReviveParticles(this.mc.theWorld, x, y, z);
            }
        } catch (Exception ignored) {}
    }
}