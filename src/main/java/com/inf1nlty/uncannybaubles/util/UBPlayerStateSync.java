package com.inf1nlty.uncannybaubles.util;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import com.inf1nlty.uncannybaubles.api.ICooldown;
import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.network.EnumPlayerStateType;
import com.inf1nlty.uncannybaubles.network.UBNetwork;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;

public final class UBPlayerStateSync {

    public static void syncAll(ServerPlayer player) {
        UBNetwork.syncPlayerState(player, EnumPlayerStateType.LAVA_IMMUNITY, ILavaImmunity.getTicks(player));
        UBNetwork.syncPlayerState(player, EnumPlayerStateType.ETERNAL_BEEF_COOLDOWN, ICooldown.get(player));
        UBNetwork.syncPlayerState(player, EnumPlayerStateType.FIERCE_KITTY_SLIPPERS_COOLDOWN, IFierceKittySlippersCooldown.get(player));
        UBNetwork.syncPlayerState(player, EnumPlayerStateType.BROKEN_ANKH_COOLDOWN, IBrokenAnkhCooldown.get(player));
    }

    public static void copyToClone(EntityPlayer source, ServerPlayer destination) {
        IBrokenAnkhCooldown.setRaw(destination, IBrokenAnkhCooldown.get(source));
        ICooldown.setRaw(destination, ICooldown.get(source));
        IFierceKittySlippersCooldown.setRaw(destination, IFierceKittySlippersCooldown.get(source));
        ILavaImmunity.setTicks(destination, ILavaImmunity.getTicks(source));
    }
}
