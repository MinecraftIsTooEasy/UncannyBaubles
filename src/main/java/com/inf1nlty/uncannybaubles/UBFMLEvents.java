package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.api.ICooldown;
import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;

import com.google.common.eventbus.Subscribe;

import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.EntityPlayer;
import net.minecraft.Packet250CustomPayload;
import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.event.SoundsRegisterEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class UBFMLEvents {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        UBItems.registerItems(event);
    }

    @Subscribe
    public void onRecipeRegister(RecipeRegistryEvent event) {
        UBRecipes.registerRecipes(event);
    }

    @Subscribe
    public void registerSounds(SoundsRegisterEvent event) {
        event.registerSound(UBSounds.fierce_kitty_slippers, 3);
        event.registerSound(UBSounds.double_jump);
    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {

        ServerPlayer player = event.getPlayer();

        if (player == null) return;

        EntityPlayer entityPlayer = player;

        if (entityPlayer instanceof ILavaImmunity immunity) {
            try {
                int ticks = immunity.ub$getLavaImmunityTicks();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);
                dos.writeInt(ticks);
                player.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|LavaImm", bos.toByteArray())
                );
            } catch (Exception ignored) {}
        }

        if (entityPlayer instanceof ICooldown cooldown) {
            try {
                int cooldownTicks = cooldown.ub$getEternalBeefCooldown();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);
                dos.writeInt(cooldownTicks);
                player.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|SteakCD", bos.toByteArray())
                );
            } catch (Exception ignored) {}
        }

        if (entityPlayer instanceof IFierceKittySlippersCooldown fierceKittyCooldown) {
            try {
                int fierceCooldownTicks = fierceKittyCooldown.ub$getFierceKittySlippersCooldown();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);
                dos.writeInt(fierceCooldownTicks);
                player.playerNetServerHandler.sendPacketToPlayer(
                    new Packet250CustomPayload("UB|FierceCD", bos.toByteArray())
                );
            } catch (Exception ignored) {}
        }
    }
}