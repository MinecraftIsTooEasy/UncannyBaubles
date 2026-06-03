package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.UBPlayerStateSync;

import com.google.common.eventbus.Subscribe;

import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.event.SoundsRegisterEvent;

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
        event.registerSound(UBSounds.hermes_boots_run);
        event.registerSound(UBSounds.broken_ankh_totem);
        event.registerSound(UBSounds.digging_claws_dig,3);
    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        UBPlayerStateSync.syncAll(event.getPlayer());
    }
}
