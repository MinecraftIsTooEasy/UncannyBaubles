package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import net.minecraft.ItemStack;
import net.minecraft.Material;

public class ItemRegenBracelet extends ItemBaseBaubles {

    public ItemRegenBracelet(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BRACELET;
    }
}