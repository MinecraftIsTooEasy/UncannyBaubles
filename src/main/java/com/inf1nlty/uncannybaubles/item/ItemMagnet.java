package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;

import com.inf1nlty.uncannybaubles.api.IAttractableItem;

import net.minecraft.*;

import java.util.List;

public class ItemMagnet extends ItemBaseBaubles {

    public ItemMagnet(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void onWornTick(ItemStack itemStack, EntityLivingBase entity) {
        if (itemStack == null) return;
        if (!(entity instanceof ServerPlayer player)) return;
        List nearbyEntities = player.getNearbyEntities(9.0F, 9.0F);
        for (Object nearbyEntity : nearbyEntities) {
            if (nearbyEntity instanceof EntityItem entityItem) {
                if (entityItem.isDead) continue;
                ((IAttractableItem) entityItem).ub$setAttractedByMagnet();
                entityItem.onCollideWithPlayer(player);
            }
        }
    }
}