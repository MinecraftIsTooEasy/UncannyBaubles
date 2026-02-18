package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.ItemStack;
import net.minecraft.FurnaceRecipes;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class UBRecipes {

    public static void registerRecipes(RecipeRegistryEvent event) {

        event.registerShapelessRecipe(new ItemStack(UBItems.lava_walking_boots, 1), false, UBItems.water_walking_boots, UBItems.lava_charm);

        if (UBItems.eternal_steak != null && UBItems.eternal_cooked_steak != null) {
            FurnaceRecipes.smelting().addSmelting(
                UBItems.eternal_steak.itemID,
                new ItemStack(UBItems.eternal_cooked_steak)
            );
        }
    }
}