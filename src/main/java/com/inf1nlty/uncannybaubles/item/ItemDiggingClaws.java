package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import net.minecraft.*;

public class ItemDiggingClaws extends ItemBaseBaubles {

    public ItemDiggingClaws(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HAND;
    }
}