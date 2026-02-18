package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import net.minecraft.*;

public class ItemBezoar extends ItemBaseBaubles {

    public ItemBezoar(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.CHARM;
    }
}