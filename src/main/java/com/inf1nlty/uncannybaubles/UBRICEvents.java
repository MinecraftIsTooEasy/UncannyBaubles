package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.events.PocketPistonCombatListener;
import com.inf1nlty.uncannybaubles.events.UBChestLootListener;
import com.inf1nlty.uncannybaubles.item.UBItems;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.handler.TradingHandler;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.MerchantRecipe;

public class UBRICEvents extends Handlers {

    @SuppressWarnings("unchecked")
    public static void register() {
        Handlers.Combat.register(new PocketPistonCombatListener());
        Handlers.LootTable.register(new UBChestLootListener());

//        Handlers.LootTable.register(new LootTableDebugListener());

        TradingHandler.Butcher.addEntry((recipeList, villager, rand) -> {

            if (rand.nextFloat() < villager.adjustProbability(0.1F)) {

                recipeList.add(new MerchantRecipe(
                        new ItemStack(Item.ingotMithril, 8),
                        new ItemStack(Item.emerald, 32),
                        new ItemStack(UBItems.eternal_cooked_steak, 1)
                ));
            }
        });
    }
}
