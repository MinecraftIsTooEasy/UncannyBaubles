package com.inf1nlty.uncannybaubles.network.s2c;

import com.inf1nlty.uncannybaubles.client.ParticleHelper;
import com.inf1nlty.uncannybaubles.network.UBNetwork;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class S2CSpawnTotemParticlesPacket implements Packet {

    private final double x;
    private final double y;
    private final double z;

    public S2CSpawnTotemParticlesPacket(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public S2CSpawnTotemParticlesPacket(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readDouble(), packetByteBuf.readDouble(), packetByteBuf.readDouble());
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeDouble(this.x);
        packetByteBuf.writeDouble(this.y);
        packetByteBuf.writeDouble(this.z);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (player != null && player.worldObj != null) {
            ParticleHelper.spawnTotemReviveParticles(player.worldObj, this.x, this.y, this.z);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return UBNetwork.S2C_SPAWN_TOTEM_PARTICLES;
    }
}
