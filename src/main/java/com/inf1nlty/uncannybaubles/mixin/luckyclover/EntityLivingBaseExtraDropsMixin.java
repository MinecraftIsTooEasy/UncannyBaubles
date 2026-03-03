package com.inf1nlty.uncannybaubles.mixin.luckyclover;

import baubles.api.BaubleSlotHelper;

import com.inf1nlty.uncannybaubles.item.UBItems;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseExtraDropsMixin {

    @Shadow protected int recentlyHit;

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void ub$extraDrop(DamageSource source, CallbackInfo ci)
    {
        EntityLivingBase self = (EntityLivingBase) (Object) this;

        if (self.worldObj == null || self.worldObj.isRemote) return;

        if (self instanceof EntityPlayer) return;

        if (recentlyHit <= 0) return;

        Entity killer = source.getResponsibleEntity();

        if (!(killer instanceof EntityPlayer killerPlayer)) return;

        if (!BaubleSlotHelper.hasHeadOfType(killerPlayer, UBItems.lucky_clover)) return;

        Random rand = self.worldObj.rand;
        
        if (self instanceof EntityMagmaCube magma) {
            if (magma.getSize() <= 1) {
                self.dropItem(Item.magmaCream.itemID, 1);
            }
        }

        else if (self instanceof EntityGelatinousCube cube) {
            if (cube.getSize() <= 1) {
                self.dropItem(Item.slimeBall.itemID, 1);
            }
        }



        else if (self instanceof EntityPigZombie) {
            self.dropItem(Item.goldNugget.itemID, 1);
        }

        else if (self instanceof EntityWight) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }

        else if (self instanceof EntityGhoul) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }

        else if (self instanceof EntityInvisibleStalker) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }

        else if (self instanceof EntityShadow) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }

        else if (self instanceof EntityGiantZombie) {
            self.dropItem(Item.ancientMetalNugget, 1);
        }

        else if (self instanceof EntityRevenant) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }

        else if (self instanceof EntityZombie) {
            self.dropItem(Item.rottenFlesh.itemID, 1);
        }



        else if (self instanceof EntityAncientBoneLord) {
            self.dropItem(Item.bone.itemID, 1);
        }

        else if (self instanceof EntityBoneLord) {
            self.dropItem(Item.bone.itemID, 1);
        }

        else if (self instanceof EntityLongdeadGuardian) {
            self.dropItem(Item.bone.itemID, 1);
        }

        else if (self instanceof EntityLongdead) {
            self.dropItem(Item.bone.itemID, 1);
        }

        else if (self instanceof EntitySkeleton skeleton) {
            if (skeleton.getSkeletonType() == 1)
            {
                self.dropItem(Item.coal.itemID, 1);
            }
            else
            {
                self.dropItem(Item.bone.itemID, 1);
            }
        }



        else if (self instanceof EntityWoodSpider) {
            self.dropItem(Item.spiderEye.itemID, 1);
        }

        else if (self instanceof EntityPhaseSpider) {
            self.dropItem(Item.silk.itemID, 1);
        }

        else if (self instanceof EntityArachnid) {
            self.dropItem(Item.silk.itemID, 1);
        }



        else if (self instanceof EntityInfernalCreeper) {
            self.dropItem(Item.gunpowder.itemID, 1);
        }

        else if (self instanceof EntityCreeper) {
            self.dropItem(Item.gunpowder.itemID, 1);
        }



        else if (self instanceof EntityFireElemental) {
            self.dropItem(Item.magmaCream.itemID, 1);
        }

        else if (self instanceof EntityClayGolem) {
            self.dropItem((Block.blockClay.blockID), 1);
        }

        else if (self instanceof EntityEarthElemental) {
            self.dropItem(Block.cobblestone.blockID, 1);
        }



        else if (self instanceof EntityWitch) {
            self.dropItem(Item.netherStalkSeeds.itemID, 1);
        }



        else if (self instanceof EntityHellhound) {
            self.dropItem(Item.blazePowder.itemID, 1);
        }

        else if (self instanceof EntityBlaze) {
            self.dropItem(Item.blazeRod.itemID, 1);
        }



        else if (self instanceof EntityEnderman) {
            self.dropItem(Item.enderPearl.itemID, 1);
        }



        else if (self instanceof EntityGhast) {
            self.dropItem(Item.ghastTear.itemID, 1);
        }



        else if (self instanceof EntityBat) {
             self.dropItem(Item.leather.itemID, 1);
        }

        else if (self instanceof EntityGiantVampireBat) {
            self.dropItem(Item.leather.itemID, 1);
        }

        else if (self instanceof EntityVampireBat) {
            self.dropItem(Item.leather.itemID, 1);
        }

        else if (self instanceof EntityNightwing) {
            self.dropItem(Item.leather.itemID, 1);
        }



        else if (self instanceof EntityCopperspine) {
            self.dropItem(Item.copperNugget.itemID, 1);
        }

        else if (self instanceof EntityNetherspawn) {
            self.dropItem(Item.gunpowder.itemID, 1);
        }

        else if (self instanceof EntityHoarySilverfish) {
            self.dropItem(Item.ancientMetalNugget.itemID, 1);
        }

        else if (self instanceof EntitySilverfish) {
            self.dropItem(Block.cobblestone.blockID, 1);
        }



        else if (self instanceof EntitySnowman) {
            self.dropItem(Item.snowball.itemID, 1);
        }

        else if (self instanceof EntityIronGolem) {
            self.dropItem(Item.ironNugget.itemID, 1);
        }



        else if (self instanceof EntityMooshroom) {
            if (rand.nextBoolean())
            {
                self.dropItem(Block.mushroomRed.blockID, 1);
            }
            else
            {
                self.dropItem(self.isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
            }
        }

        else if (self instanceof EntityCow) {
            if (rand.nextBoolean())
            {
                self.dropItem(Item.leather.itemID, 1);
            }
            else
            {
                self.dropItem(self.isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
            }
        }

        else if (self instanceof EntityChicken) {
            if (rand.nextBoolean())
            {
                self.dropItem(Item.feather.itemID, 1);
            }
            else
            {
                self.dropItem(self.isBurning() ? Item.chickenCooked.itemID : Item.chickenRaw.itemID, 1);
            }
        }

        else if (self instanceof EntityPig) {
            self.dropItem(self.isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID, 1);
        }

        else if (self instanceof EntitySheep sheep) {
            if (rand.nextBoolean())
            {
                self.dropItemStack(new ItemStack(Block.cloth.blockID, 1, sheep.getFleeceColor()));
            }
            else
            {
                self.dropItem(self.isBurning() ? Item.lambchopCooked.itemID : Item.lambchopRaw.itemID, 1);
            }
        }



        else if (self instanceof EntityDireWolf) {
            self.dropItem(Item.leather.itemID, 1);
        }

        else if (self instanceof EntityWolf) {
            self.dropItem(Item.leather.itemID, 1);
        }



        else if (self instanceof EntityOcelot) {
            self.dropItem(Item.fishRaw.itemID, 1);
        }

        else if (self instanceof EntityHorse horse) {
            int type = horse.getHorseType();
            if (type == 4)
            {
                self.dropItem(Item.bone.itemID, 1);
            }
            else if (type == 3)
            {
                self.dropItem(Item.rottenFlesh.itemID, 1);
            }
            else
            {
                self.dropItem(Item.leather.itemID, 1);
            }
        }



        else if (self instanceof EntitySquid) {
            self.dropItem(Item.dyePowder.itemID, 1);
        }
    }
}