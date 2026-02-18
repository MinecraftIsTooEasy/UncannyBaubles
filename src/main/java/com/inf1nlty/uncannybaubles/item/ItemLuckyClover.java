package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import net.minecraft.ItemStack;
import net.minecraft.Material;

public class ItemLuckyClover extends ItemBaseBaubles {

    public ItemLuckyClover(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HEAD;
    }
}