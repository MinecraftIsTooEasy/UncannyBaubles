package com.inf1nlty.uncannybaubles.client;

import net.minecraft.World;

public class ParticleHelper {

    public static void spawnTotemReviveParticles(World world, double x, double y, double z) {
        EntityTotemFX.Provider.spawn(world, x, y, z);
    }
}