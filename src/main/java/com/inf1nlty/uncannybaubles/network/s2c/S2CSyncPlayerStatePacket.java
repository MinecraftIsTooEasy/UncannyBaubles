package com.inf1nlty.uncannybaubles.network.s2c;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import com.inf1nlty.uncannybaubles.api.ICooldown;
import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.network.UBNetwork;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class S2CSyncPlayerStatePacket implements Packet {

    private final EnumPlayerStateType type;
    private final int value;

    public S2CSyncPlayerStatePacket(EnumPlayerStateType type, int value) {
        this.type = type;
        this.value = value;
    }

    public S2CSyncPlayerStatePacket(PacketByteBuf packetByteBuf) {
        this(EnumPlayerStateType.byId(packetByteBuf.readByte()), packetByteBuf.readInt());
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeByte(this.type.getId());
        packetByteBuf.writeInt(this.value);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (this.type == null) return;

        switch (this.type) {
            case LAVA_IMMUNITY -> ILavaImmunity.setTicks(player, this.value);
            case ETERNAL_BEEF_COOLDOWN -> ICooldown.set(player, this.value);
            case FIERCE_KITTY_SLIPPERS_COOLDOWN -> IFierceKittySlippersCooldown.set(player, this.value);
            case BROKEN_ANKH_COOLDOWN -> IBrokenAnkhCooldown.set(player, this.value);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return UBNetwork.S2C_SYNC_PLAYER_STATE;
    }
}
