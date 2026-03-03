package com.inf1nlty.uncannybaubles.mixin.client;

import com.inf1nlty.uncannybaubles.client.ParticleSpawnQueue;

import net.minecraft.EffectRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectRenderer.class)
public abstract class EffectRendererMixin {

    @Inject(method = "updateEffects", at = @At("TAIL"))
    private void ub$flushQueuedParticles(CallbackInfo ci) {
        ParticleSpawnQueue.flush();
    }
}