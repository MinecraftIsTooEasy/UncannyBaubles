package com.inf1nlty.uncannybaubles.events;

import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import net.minecraft.Block;
import net.minecraft.EnumEntityReachContext;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;

public class PocketPistonCombatListener implements ICombatListener {

    @Override
    public float onPlayerBlockReachModify(EntityPlayer player, Block block, int metadata, float original) {
        if (BaubleSlotHelper.hasHandOfType(player, UBItems.pocket_piston)) {
            original += 1.0F;
        }
        return original;
    }

    @Override
    public float onPlayerEntityReachModify(EntityPlayer player, EnumEntityReachContext context, Entity entity, float original) {
        if (BaubleSlotHelper.hasHandOfType(player, UBItems.pocket_piston)) {
            original += 1.0F;
        }
        return original;
    }
}