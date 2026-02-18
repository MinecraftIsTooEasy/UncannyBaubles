package com.inf1nlty.uncannybaubles.item;

import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.minecraft.Material;

public class UBItems {

    public static ItemMagnet magnet;
    public static ItemBottledCloud bottled_cloud;
    public static ItemPocketPiston pocket_piston;
    public static ItemLuckyClover lucky_clover;
    public static ItemBezoar bezoar;
    public static ItemRegenBracelet regen_bracelet;
    public static ItemWaterWalker water_walking_boots;
    public static ItemLavaWalker lava_walking_boots;
    public static ItemLavaCharm lava_charm;
    public static ItemEternalSteak eternal_steak;
    public static ItemEternalCookedSteak eternal_cooked_steak;
    public static ItemKittySlippers kitty_slippers;
    public static ItemFierceKittySlippers fierce_kitty_slippers;
    public static ItemLuckyHorseshoe lucky_horseshoe;

    public static void registerItems(ItemRegistryEvent event) {

        magnet = new ItemMagnet(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:magnet", "magnet", magnet);

        bottled_cloud = new ItemBottledCloud(IdUtil.getNextItemID(), Material.glass);
        event.register("UncannyBaubles", "uncannybaubles:bottled_cloud", "bottled_cloud", bottled_cloud);

        pocket_piston = new ItemPocketPiston(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:pocket_piston", "pocket_piston", pocket_piston);

        lucky_clover = new ItemLuckyClover(IdUtil.getNextItemID(), Material.grass);
        event.register("UncannyBaubles", "uncannybaubles:lucky_clover", "lucky_clover", lucky_clover);

        bezoar = new ItemBezoar(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:bezoar", "bezoar", bezoar);

        regen_bracelet = new ItemRegenBracelet(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:regen_bracelet", "regen_bracelet", regen_bracelet);

        water_walking_boots = new ItemWaterWalker(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:water_walking_boots", "water_walking_boots", water_walking_boots);

        lava_walking_boots = new ItemLavaWalker(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:lava_walking_boots", "lava_walking_boots", lava_walking_boots);

        lava_charm = new ItemLavaCharm(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:lava_charm", "lava_charm", lava_charm);

        eternal_steak = new ItemEternalSteak(IdUtil.getNextItemID());
        event.register("UncannyBaubles", "uncannybaubles:eternal_steak", "eternal_steak", eternal_steak);

        eternal_cooked_steak = new ItemEternalCookedSteak(IdUtil.getNextItemID());
        event.register("UncannyBaubles", "uncannybaubles:eternal_cooked_steak", "eternal_cooked_steak", eternal_cooked_steak);

        kitty_slippers = new ItemKittySlippers(IdUtil.getNextItemID(), Material.cloth);
        event.register("UncannyBaubles", "uncannybaubles:kitty_slippers", "kitty_slippers", kitty_slippers);

        fierce_kitty_slippers = new ItemFierceKittySlippers(IdUtil.getNextItemID(), Material.cloth);
        event.register("UncannyBaubles", "uncannybaubles:fierce_kitty_slippers", "fierce_kitty_slippers", fierce_kitty_slippers);

        lucky_horseshoe = new ItemLuckyHorseshoe(IdUtil.getNextItemID(), Material.iron);
        event.register("UncannyBaubles", "uncannybaubles:lucky_horseshoe", "lucky_horseshoe", lucky_horseshoe);
    }
}