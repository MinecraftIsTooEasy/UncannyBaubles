package com.inf1nlty.uncannybaubles.events;

import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

final class ChestLootEntries {

    static void add(List<WeightedRandomChestContent> entries, Item item, double probability, int min, int max, int weight) {
        if (item == null || probability <= 0.0D) return;
        entries.add(new WeightedRandomChestContent(new ItemStack(item, 1), min, max, weight));
    }
}
