package com.inf1nlty.uncannybaubles.compat;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.util.FoodDataList;
import net.xiaoyu233.fml.FishModLoader;

public class ModCompat {

    public static final boolean HAS_ITFRB = FishModLoader.hasMod("mite-itf-reborn");

    public static void callITFRBFoodDataOnEaten(ItemStack stack, EntityPlayer player) {
        FoodDataList.onFoodEaten(stack, player);
    }
}