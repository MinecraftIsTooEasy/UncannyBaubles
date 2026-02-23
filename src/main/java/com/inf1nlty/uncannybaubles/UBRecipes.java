package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.FurnaceRecipes;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class UBRecipes {

    public static void registerRecipes(RecipeRegistryEvent event) {

        event.registerShapedRecipe(new ItemStack(UBItems.magnet, 1), false, "IIL", "I  ", "IIR", 'I', Item.ingotIron, 'L', new ItemStack(Item.dyePowder, 1, 4), 'R', Item.redstone);
        event.registerShapelessRecipe(new ItemStack(UBItems.lava_walking_boots, 1), false, UBItems.water_walking_boots, UBItems.lava_charm);
        event.registerShapedRecipe(new ItemStack(UBItems.bottled_cloud, 1), false, "GQG", "GMG", "GCG", 'G', Block.glass, 'Q', Item.netherQuartz, 'M', Item.bootsMithril, 'C', Block.cloth);

        if (UBItems.eternal_steak != null && UBItems.eternal_cooked_steak != null) {
            FurnaceRecipes.smelting().addSmelting(
                UBItems.eternal_steak.itemID,
                new ItemStack(UBItems.eternal_cooked_steak)
            );
        }
    }
}