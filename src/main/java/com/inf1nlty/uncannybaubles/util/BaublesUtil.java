package com.inf1nlty.uncannybaubles.util;

import baubles.api.BaublesApi;
import net.minecraft.IInventory;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.EntityPlayer;

import java.util.Random;

public final class BaublesUtil {

    private BaublesUtil() {}

    public static boolean hasBaubleWorn(EntityPlayer player, Item target) {

        if (player == null || target == null) return false;

        IInventory inv = BaublesApi.getBaubles(player);

        if (inv == null) return false;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null && stack.getItem() == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean rollChance(Random rand, double probability) {
        if (probability <= 0.0) return false;
        if (probability >= 1.0) return true;
        if (rand == null) rand = new Random();
        return rand.nextDouble() < probability;
    }
}