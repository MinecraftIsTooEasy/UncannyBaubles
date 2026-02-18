package com.inf1nlty.uncannybaubles;

import com.inf1nlty.uncannybaubles.debug.LootTableDebugListener;
import com.inf1nlty.uncannybaubles.events.BottledCloudLootListener;
import com.inf1nlty.uncannybaubles.events.FierceKittySlippersLootListener;
import com.inf1nlty.uncannybaubles.events.KittySlippersLootListener;
import com.inf1nlty.uncannybaubles.events.LavaCharmLootListener;
import com.inf1nlty.uncannybaubles.events.PocketPistonCombatListener;
import com.inf1nlty.uncannybaubles.events.PocketPistonLootListener;
import com.inf1nlty.uncannybaubles.events.RegenRingLootListener;
import com.inf1nlty.uncannybaubles.events.WaterWalkerFishingListener;
import moddedmite.rustedironcore.api.event.Handlers;

public class UBRICEvents extends Handlers {

    public static void register() {
        Handlers.Combat.register(new PocketPistonCombatListener());
        Handlers.LootTable.register(new KittySlippersLootListener());
        Handlers.LootTable.register(new FierceKittySlippersLootListener());
        Handlers.LootTable.register(new PocketPistonLootListener());
        Handlers.LootTable.register(new LavaCharmLootListener());
        Handlers.LootTable.register(new BottledCloudLootListener());
        Handlers.LootTable.register(new RegenRingLootListener());
        Handlers.LootTable.register(new WaterWalkerFishingListener());

//        Handlers.LootTable.register(new LootTableDebugListener());
    }
}