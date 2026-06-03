package com.inf1nlty.uncannybaubles.network;

import com.inf1nlty.uncannybaubles.UncannyBaublesMod;
import com.inf1nlty.uncannybaubles.network.s2c.S2CSpawnTotemParticlesPacket;
import com.inf1nlty.uncannybaubles.network.s2c.S2CSyncPlayerStatePacket;
import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

public final class UBNetwork {

    public static final ResourceLocation S2C_SYNC_PLAYER_STATE = new ResourceLocation(UncannyBaublesMod.NAMESPACE, "sync_player_state");
    public static final ResourceLocation S2C_SPAWN_TOTEM_PARTICLES = new ResourceLocation(UncannyBaublesMod.NAMESPACE, "spawn_totem_particles");

    public static void registerClientReaders() {
        PacketReader.registerClientPacketReader(S2C_SYNC_PLAYER_STATE, S2CSyncPlayerStatePacket::new);
        PacketReader.registerClientPacketReader(S2C_SPAWN_TOTEM_PARTICLES, S2CSpawnTotemParticlesPacket::new);
    }

    public static void syncPlayerState(ServerPlayer player, EnumPlayerStateType type, int value) {
        Network.sendToClient(player, new S2CSyncPlayerStatePacket(type, value));
    }

    public static void spawnTotemParticles(ServerPlayer player, double x, double y, double z) {
        Network.sendToClient(player, new S2CSpawnTotemParticlesPacket(x, y, z));
    }
}
