package com.inf1nlty.uncannybaubles.feature.baubledrops;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.RandomUtil;
import net.minecraft.EntityAncientBoneLord;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityCow;
import net.minecraft.EntityFireElemental;
import net.minecraft.EntityGhast;
import net.minecraft.EntityHorse;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPhaseSpider;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySilverfish;
import net.minecraft.EntityWitch;
import net.minecraft.Item;
import net.minecraft.World;

public final class BaubleDropRules {

    public static void apply(EntityLivingBase entity) {
        if (entity.worldObj.isRemote) return;
        if (entity instanceof EntityPlayer) return;

        dropRareBaubles(entity);
        dropLuckyClover(entity);
    }

    private static void dropRareBaubles(EntityLivingBase entity) {
        tryDrop(entity, entity instanceof EntityArachnid, UBItems.bezoar, UBConfigs.spiderBezoarDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityFireElemental, UBItems.lava_charm, UBConfigs.fireElementalLavaCharmDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityGhast, UBItems.shiny_red_balloon, UBConfigs.ghastShinyRedBalloonDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityCow, UBItems.eternal_steak, UBConfigs.cowEternalSteakDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityWitch, UBItems.regen_bracelet, UBConfigs.witchRegenRingDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityHorse, UBItems.lucky_horseshoe, UBConfigs.horseLuckyHorseshoeDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityPhaseSpider, UBItems.hermes_boots, UBConfigs.hermesBootsPhaseSpiderDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntityAncientBoneLord, UBItems.cross_necklace, UBConfigs.ancientBoneLordNecklaceDropProbability.getDoubleValue());
        tryDrop(entity, entity instanceof EntitySilverfish, UBItems.digging_claws, UBConfigs.diggingClawsSilverfishDropProbability.getDoubleValue());
    }

    private static void dropLuckyClover(EntityLivingBase entity) {
        tryDrop(entity, true, UBItems.lucky_clover, UBConfigs.luckyCloverDropProbability.getDoubleValue());
    }

    private static void tryDrop(EntityLivingBase entity, boolean matches, Item item, double probability) {
        if (!matches || item == null) return;

        World world = entity.worldObj;
        if (RandomUtil.rollChance(world.rand, probability)) {
            entity.dropItem(item);
        }
    }
}
