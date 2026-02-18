package com.inf1nlty.uncannybaubles.debug;

import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.ItemStack;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class LootTableDebugListener implements ILootTableRegisterListener {

    private void printLootTable(String name, List<WeightedRandomChestContent> original) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + String.format("%-62s", name + " Loot Table") + " ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");

        int totalWeight = 0;

        for (int i = 0; i < original.size(); i++) {
            WeightedRandomChestContent content = original.get(i);
            int weight = content.itemWeight;
            ItemStack item = content.theItemId;

            totalWeight += weight;

            String itemName = item.getItem().getUnlocalizedName();
            if (itemName == null) {
                itemName = "Unknown";
            }

            System.out.println(String.format("║ [%2d] %-30s W:%3d  Qty:%2d-%-2d ║",
                i,
                itemName.substring(0, Math.min(30, itemName.length())),
                weight,
                content.min_quantity,
                content.max_quantity));
        }

        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ " + String.format("%-50s", "Total Weight: " + totalWeight) + "         ║");

        // 计算你的物品权重为 1, 2, 3, 5, 10 时的概率
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Probability for your item (assuming 8 draws):                 ║");

        int[] testWeights = {1, 2, 3, 5, 10};
        for (int w : testWeights) {
            double singleDrawProb = (double) w / (totalWeight + w);
            double atLeastOne = 1 - Math.pow(1 - singleDrawProb, 8);
            System.out.println(String.format("║   Weight %2d: %.2f%% (single: %.2f%%)                        ║",
                w, atLeastOne * 100, singleDrawProb * 100));
        }

        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }

    @Override
    public void onDesertPyramidRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Desert Pyramid (沙漠神殿)", original);
    }

    @Override
    public void onJunglePyramidRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Jungle Pyramid (丛林神庙)", original);
    }

    @Override
    public void onFortressRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Nether Fortress (下界要塞)", original);
    }

    @Override
    public void onMineshaftRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Mineshaft (废弃矿井)", original);
    }

    @Override
    public void onStrongholdCorridorRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Stronghold Corridor (要塞走廊)", original);
    }

    @Override
    public void onStrongholdCrossingRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Stronghold Crossing (要塞交叉口)", original);
    }

    @Override
    public void onStrongholdLibraryRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Stronghold Library (要塞图书馆)", original);
    }

    @Override
    public void onSwampHutRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Swamp Hut (女巫小屋)", original);
    }

    @Override
    public void onBlackSmithRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Village Blacksmith (村庄铁匠铺)", original);
    }

    @Override
    public void onDungeonOverworldRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Dungeon Overworld (主世界地牢)", original);
    }

    @Override
    public void onDungeonUnderworldRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Dungeon Underworld (地下世界地牢)", original);
    }

    @Override
    public void onFishingRegister(List<WeightedRandomChestContent> original) {
        printLootTable("Fishing Treasure (钓鱼宝藏)", original);
    }
}