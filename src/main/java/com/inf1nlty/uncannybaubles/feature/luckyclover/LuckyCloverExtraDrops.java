package com.inf1nlty.uncannybaubles.feature.luckyclover;

import net.minecraft.Block;
import net.minecraft.EntityAncientBoneLord;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityBat;
import net.minecraft.EntityBlaze;
import net.minecraft.EntityBoneLord;
import net.minecraft.EntityChicken;
import net.minecraft.EntityClayGolem;
import net.minecraft.EntityCopperspine;
import net.minecraft.EntityCow;
import net.minecraft.EntityCreeper;
import net.minecraft.EntityDireWolf;
import net.minecraft.EntityEarthElemental;
import net.minecraft.EntityEnderman;
import net.minecraft.Entity;
import net.minecraft.EntityFireElemental;
import net.minecraft.EntityGelatinousCube;
import net.minecraft.EntityGhast;
import net.minecraft.EntityGhoul;
import net.minecraft.EntityGiantVampireBat;
import net.minecraft.EntityGiantZombie;
import net.minecraft.EntityHellhound;
import net.minecraft.EntityHoarySilverfish;
import net.minecraft.EntityHorse;
import net.minecraft.EntityInfernalCreeper;
import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityIronGolem;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLongdead;
import net.minecraft.EntityLongdeadGuardian;
import net.minecraft.EntityMagmaCube;
import net.minecraft.EntityMooshroom;
import net.minecraft.EntityNetherspawn;
import net.minecraft.EntityNightwing;
import net.minecraft.EntityOcelot;
import net.minecraft.EntityPhaseSpider;
import net.minecraft.EntityPig;
import net.minecraft.EntityPigZombie;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityRevenant;
import net.minecraft.EntityShadow;
import net.minecraft.EntitySheep;
import net.minecraft.EntitySilverfish;
import net.minecraft.EntitySkeleton;
import net.minecraft.EntitySnowman;
import net.minecraft.EntitySquid;
import net.minecraft.EntityVampireBat;
import net.minecraft.EntityWight;
import net.minecraft.EntityWitch;
import net.minecraft.EntityWolf;
import net.minecraft.EntityWoodSpider;
import net.minecraft.EntityZombie;
import net.minecraft.DamageSource;
import net.minecraft.Item;
import net.minecraft.ItemStack;

import java.util.Random;

public final class LuckyCloverExtraDrops {

    public static void applyIfEligible(EntityLivingBase entity, DamageSource source, int recentlyHit) {
        if (entity.worldObj.isRemote) return;
        if (entity instanceof EntityPlayer) return;
        if (source == null || recentlyHit <= 0) return;

        Entity killer = source.getResponsibleEntity();
        if (!(killer instanceof EntityPlayer player) || !LuckyCloverEffect.hasLuckyClover(player)) return;

        apply(entity);
    }

    public static void apply(EntityLivingBase entity) {
        Random rand = entity.worldObj.rand;

        if (entity instanceof EntityMagmaCube magma) {
            if (magma.getSize() <= 1) {
                entity.dropItem(Item.magmaCream.itemID, 1);
            }
        }
        else if (entity instanceof EntityGelatinousCube cube) {
            if (cube.getSize() <= 1) {
                entity.dropItem(Item.slimeBall.itemID, 1);
            }
        }
        else if (entity instanceof EntityPigZombie) {
            entity.dropItem(Item.goldNugget.itemID, 1);
        }
        else if (entity instanceof EntityWight) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityGhoul) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityInvisibleStalker) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityShadow) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityGiantZombie) {
            entity.dropItem(Item.ancientMetalNugget, 1);
        }
        else if (entity instanceof EntityRevenant) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityZombie) {
            entity.dropItem(Item.rottenFlesh.itemID, 1);
        }
        else if (entity instanceof EntityAncientBoneLord) {
            entity.dropItem(Item.bone.itemID, 1);
        }
        else if (entity instanceof EntityBoneLord) {
            entity.dropItem(Item.bone.itemID, 1);
        }
        else if (entity instanceof EntityLongdeadGuardian) {
            entity.dropItem(Item.bone.itemID, 1);
        }
        else if (entity instanceof EntityLongdead) {
            entity.dropItem(Item.bone.itemID, 1);
        }
        else if (entity instanceof EntitySkeleton skeleton) {
            entity.dropItem(skeleton.getSkeletonType() == 1 ? Item.coal.itemID : Item.bone.itemID, 1);
        }
        else if (entity instanceof EntityWoodSpider) {
            entity.dropItem(Item.spiderEye.itemID, 1);
        }
        else if (entity instanceof EntityPhaseSpider) {
            entity.dropItem(Item.silk.itemID, 1);
        }
        else if (entity instanceof EntityArachnid) {
            entity.dropItem(Item.silk.itemID, 1);
        }
        else if (entity instanceof EntityInfernalCreeper) {
            entity.dropItem(Item.gunpowder.itemID, 1);
        }
        else if (entity instanceof EntityCreeper) {
            entity.dropItem(Item.gunpowder.itemID, 1);
        }
        else if (entity instanceof EntityFireElemental) {
            entity.dropItem(Item.magmaCream.itemID, 1);
        }
        else if (entity instanceof EntityClayGolem) {
            entity.dropItem(Block.blockClay.blockID, 1);
        }
        else if (entity instanceof EntityEarthElemental) {
            entity.dropItem(Block.cobblestone.blockID, 1);
        }
        else if (entity instanceof EntityWitch) {
            entity.dropItem(Item.netherStalkSeeds.itemID, 1);
        }
        else if (entity instanceof EntityHellhound) {
            entity.dropItem(Item.blazePowder.itemID, 1);
        }
        else if (entity instanceof EntityBlaze) {
            entity.dropItem(Item.blazeRod.itemID, 1);
        }
        else if (entity instanceof EntityEnderman) {
            entity.dropItem(Item.enderPearl.itemID, 1);
        }
        else if (entity instanceof EntityGhast) {
            entity.dropItem(Item.ghastTear.itemID, 1);
        }
        else if (entity instanceof EntityBat) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityGiantVampireBat) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityVampireBat) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityNightwing) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityCopperspine) {
            entity.dropItem(Item.copperNugget.itemID, 1);
        }
        else if (entity instanceof EntityNetherspawn) {
            entity.dropItem(Item.gunpowder.itemID, 1);
        }
        else if (entity instanceof EntityHoarySilverfish) {
            entity.dropItem(Item.ancientMetalNugget.itemID, 1);
        }
        else if (entity instanceof EntitySilverfish) {
            entity.dropItem(Block.cobblestone.blockID, 1);
        }
        else if (entity instanceof EntitySnowman) {
            entity.dropItem(Item.snowball.itemID, 1);
        }
        else if (entity instanceof EntityIronGolem) {
            entity.dropItem(Item.ironNugget.itemID, 1);
        }
        else if (entity instanceof EntityMooshroom) {
            if (rand.nextBoolean()) {
                entity.dropItem(Block.mushroomRed.blockID, 1);
            } else {
                entity.dropItem(entity.isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
            }
        }
        else if (entity instanceof EntityCow) {
            if (rand.nextBoolean()) {
                entity.dropItem(Item.leather.itemID, 1);
            } else {
                entity.dropItem(entity.isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
            }
        }
        else if (entity instanceof EntityChicken) {
            if (rand.nextBoolean()) {
                entity.dropItem(Item.feather.itemID, 1);
            } else {
                entity.dropItem(entity.isBurning() ? Item.chickenCooked.itemID : Item.chickenRaw.itemID, 1);
            }
        }
        else if (entity instanceof EntityPig) {
            entity.dropItem(entity.isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID, 1);
        }
        else if (entity instanceof EntitySheep sheep) {
            if (rand.nextBoolean()) {
                entity.dropItemStack(new ItemStack(Block.cloth.blockID, 1, sheep.getFleeceColor()));
            } else {
                entity.dropItem(entity.isBurning() ? Item.lambchopCooked.itemID : Item.lambchopRaw.itemID, 1);
            }
        }
        else if (entity instanceof EntityDireWolf) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityWolf) {
            entity.dropItem(Item.leather.itemID, 1);
        }
        else if (entity instanceof EntityOcelot) {
            entity.dropItem(Item.fishRaw.itemID, 1);
        }
        else if (entity instanceof EntityHorse horse) {
            int type = horse.getHorseType();
            if (type == 4) {
                entity.dropItem(Item.bone.itemID, 1);
            } else if (type == 3) {
                entity.dropItem(Item.rottenFlesh.itemID, 1);
            } else {
                entity.dropItem(Item.leather.itemID, 1);
            }
        }
        else if (entity instanceof EntitySquid) {
            entity.dropItem(Item.dyePowder.itemID, 1);
        }
    }
}
