package com.inf1nlty.uncannybaubles.feature.crossnecklace;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;

public final class CrossNecklaceEffect {

    public static int getHurtResistantTime(Entity entity, int defaultTime) {
        if (!(entity instanceof EntityPlayer player)) return defaultTime;
        if (player.worldObj.isRemote) return defaultTime;

        return BaubleSlotHelper.hasAmuletOfType(player, UBItems.cross_necklace)
            ? 40
            : 20;
    }
}
