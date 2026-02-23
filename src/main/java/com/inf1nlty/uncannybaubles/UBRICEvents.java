package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.events.BottledCloudLootListener;
import com.inf1nlty.uncannybaubles.events.FierceKittySlippersLootListener;
import com.inf1nlty.uncannybaubles.events.KittySlippersLootListener;
import com.inf1nlty.uncannybaubles.events.LavaCharmLootListener;
import com.inf1nlty.uncannybaubles.events.PocketPistonCombatListener;
import com.inf1nlty.uncannybaubles.events.PocketPistonLootListener;
import com.inf1nlty.uncannybaubles.events.RegenBraceletLootListener;
import com.inf1nlty.uncannybaubles.events.WaterWalkerLootListener;
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
        Handlers.LootTable.register(new KittySlippersLootListener());
        Handlers.LootTable.register(new FierceKittySlippersLootListener());
        Handlers.LootTable.register(new PocketPistonLootListener());
        Handlers.LootTable.register(new LavaCharmLootListener());
        Handlers.LootTable.register(new BottledCloudLootListener());
        Handlers.LootTable.register(new RegenBraceletLootListener());
        Handlers.LootTable.register(new WaterWalkerLootListener());

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