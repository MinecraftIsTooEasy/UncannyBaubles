package com.inf1nlty.uncannybaubles.util;

import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.network.UBNetwork;
import net.minecraft.EntityPlayer;
import net.minecraft.NBTTagCompound;
import net.minecraft.ServerPlayer;

public final class PlayerIntState {

    private final String nbtKey;
    private final EnumPlayerStateType syncType;
    private final int maxValue;
    private int value;

    public PlayerIntState(String nbtKey, EnumPlayerStateType syncType) {
        this(nbtKey, syncType, Integer.MAX_VALUE);
    }

    public PlayerIntState(String nbtKey, EnumPlayerStateType syncType, int maxValue) {
        this.nbtKey = nbtKey;
        this.syncType = syncType;
        this.maxValue = maxValue;
    }

    public int get() {
        return this.value;
    }

    public void setRaw(int value) {
        this.value = clamp(value);
    }

    public void setAndSync(EntityPlayer player, int value) {
        setRaw(value);
        sync(player);
    }

    public void tickDown() {
        if (this.value > 0) {
            this.value--;
        }
    }

    public void add(int delta) {
        setRaw(this.value + delta);
    }

    public void write(NBTTagCompound nbt) {
        nbt.setInteger(this.nbtKey, this.value);
    }

    public void read(NBTTagCompound nbt) {
        if (nbt.hasKey(this.nbtKey)) {
            setRaw(nbt.getInteger(this.nbtKey));
        } else {
            this.value = 0;
        }
    }

    private void sync(EntityPlayer player) {
        if (this.syncType != null && player.onServer() && player instanceof ServerPlayer serverPlayer) {
            UBNetwork.syncPlayerState(serverPlayer, this.syncType, this.value);
        }
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(value, this.maxValue));
    }
}
