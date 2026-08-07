package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import com.inf1nlty.uncannybaubles.UncannyBaublesMod;
import net.minecraft.Item;
import net.minecraft.Material;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class UBItems {

    public static ItemMagnet magnet;
    public static ItemBaseBaubles bottled_cloud;
    public static ItemBaseBaubles pocket_piston;
    public static ItemBaseBaubles lucky_clover;
    public static ItemBaseBaubles bezoar;
    public static ItemBaseBaubles regen_bracelet;
    public static ItemBaseBaubles water_walking_boots;
    public static ItemBaseBaubles lava_walking_boots;
    public static ItemBaseBaubles lava_charm;
    public static ItemEternalSteak eternal_steak;
    public static ItemEternalCookedSteak eternal_cooked_steak;
    public static ItemBaseBaubles kitty_slippers;
    public static ItemBaseBaubles fierce_kitty_slippers;
    public static ItemBaseBaubles lucky_horseshoe;
    public static ItemBaseBaubles cross_necklace;
    public static ItemBaseBaubles hermes_boots;
    public static ItemBrokenAnkh broken_ankh;
    public static ItemBaseBaubles digging_claws;
    public static ItemBaseBaubles onion_ring;
    public static ItemBaseBaubles shiny_red_balloon;
    public static ItemBaseBaubles cloud_in_a_balloon;
    public static ItemBaseBaubles blue_horseshoe_balloon;

    public static void registerItems(ItemRegistryEvent event) {

        magnet = new ItemMagnet(IdUtil.getNextItemID(), Material.iron);
        register(event, "magnet", magnet);

        bottled_cloud = registerBauble(event, "bottled_cloud", Material.mithril, BaubleType.BELT);
        pocket_piston = registerBauble(event, "pocket_piston", Material.iron, BaubleType.HAND);
        lucky_clover = registerBauble(event, "lucky_clover", Material.grass, BaubleType.HEAD);
        bezoar = registerBauble(event, "bezoar", Material.iron, BaubleType.CHARM);
        regen_bracelet = registerBauble(event, "regen_bracelet", Material.iron, BaubleType.BRACELET);
        water_walking_boots = registerBauble(event, "water_walking_boots", Material.iron, BaubleType.FEET);
        lava_walking_boots = registerBauble(event, "lava_walking_boots", Material.iron, BaubleType.FEET);
        lava_charm = registerBauble(event, "lava_charm", Material.iron, BaubleType.CHARM);

        eternal_steak = new ItemEternalSteak(IdUtil.getNextItemID());
        register(event, "eternal_steak", eternal_steak);

        eternal_cooked_steak = new ItemEternalCookedSteak(IdUtil.getNextItemID());
        register(event, "eternal_cooked_steak", eternal_cooked_steak);

        kitty_slippers = registerBauble(event, "kitty_slippers", Material.cloth, BaubleType.FEET);
        fierce_kitty_slippers = registerBauble(event, "fierce_kitty_slippers", Material.cloth, BaubleType.FEET);
        lucky_horseshoe = registerBauble(event, "lucky_horseshoe", Material.iron, BaubleType.FEET);
        cross_necklace = registerBauble(event, "cross_necklace", Material.iron, BaubleType.AMULET);
        hermes_boots = registerBauble(event, "hermes_boots", Material.iron, BaubleType.FEET);

        broken_ankh = new ItemBrokenAnkh(IdUtil.getNextItemID(), Material.iron);
        register(event, "broken_ankh", broken_ankh);

        digging_claws = registerBauble(event, "digging_claws", Material.iron, BaubleType.HAND);
        onion_ring = registerBauble(event, "onion_ring", Material.iron, BaubleType.HAND);
        shiny_red_balloon = registerBauble(event, "shiny_red_balloon", Material.cloth, BaubleType.BELT);
        cloud_in_a_balloon = registerBauble(event, "cloud_in_a_balloon", Material.mithril, BaubleType.BELT);
        blue_horseshoe_balloon = registerBauble(event, "blue_horseshoe_balloon", Material.iron, BaubleType.BELT);
    }

    private static ItemBaseBaubles registerBauble(ItemRegistryEvent event, String key, Material material, BaubleType baubleType) {
        ItemBaseBaubles item = new ItemSimpleBauble(IdUtil.getNextItemID(), material, baubleType);
        register(event, key, item);
        return item;
    }

    private static void register(ItemRegistryEvent event, String key, Item item) {
        event.register("UncannyBaubles", UncannyBaublesMod.NAMESPACE + ":" + key, key, item);
    }
}
