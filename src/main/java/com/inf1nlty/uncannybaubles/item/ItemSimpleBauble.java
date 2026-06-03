package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import net.minecraft.ItemStack;
import net.minecraft.Material;

public class ItemSimpleBauble extends ItemBaseBaubles {

    private final BaubleType baubleType;

    public ItemSimpleBauble(int id, Material material, BaubleType baubleType) {
        super(id, material);
        this.baubleType = baubleType;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return this.baubleType;
    }
}
