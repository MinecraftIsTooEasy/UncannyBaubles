package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.RandomUtil;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseDropBaublesMixin {

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void dropLuckyClover(DamageSource source, CallbackInfo ci) {

        EntityLivingBase self = (EntityLivingBase) (Object) this;

        if (self.worldObj == null || self.worldObj.isRemote) return;

        if (self instanceof EntityPlayer) return;

        if (self instanceof EntityArachnid) {
            if (UBItems.bezoar != null) {
                double spiderProb = UBConfigs.spiderBezoarDropProbability.getDoubleValue();
                if (RandomUtil.rollChance(self.worldObj.rand, spiderProb)) {
                    self.dropItem(UBItems.bezoar);
                }
            }
        }

        if (self instanceof EntityFireElemental) {
            if (UBItems.lava_charm != null) {
                double fireElementalProb = UBConfigs.fireElementalLavaCharmDropProbability.getDoubleValue();
                if (RandomUtil.rollChance(self.worldObj.rand, fireElementalProb)) {
                    self.dropItem(UBItems.lava_charm);
                }
            }
        }

        if (self instanceof EntityCow) {
            if (UBItems.eternal_steak != null) {
                double cowProb = UBConfigs.cowEternalSteakDropProbability.getDoubleValue();
                if (RandomUtil.rollChance(self.worldObj.rand, cowProb)) {
                    self.dropItem(UBItems.eternal_steak);
                }
            }
        }

        if (self instanceof EntityWitch) {
            if (UBItems.regen_bracelet != null) {
                double witchProb = UBConfigs.witchRegenRingDropProbability.getDoubleValue();
                if (RandomUtil.rollChance(self.worldObj.rand, witchProb)) {
                    self.dropItem(UBItems.regen_bracelet);
                }
            }
        }

        if (self instanceof EntityHorse) {
                if (UBItems.lucky_horseshoe != null) {
                    double muleProb = UBConfigs.horseLuckyHorseshoeDropProbability.getDoubleValue();
                    if (RandomUtil.rollChance(self.worldObj.rand, muleProb)) {
                        self.dropItem(UBItems.lucky_horseshoe);
                    }
                }
            }

        if (UBItems.lucky_clover == null) return;

        double probability = UBConfigs.luckyCloverDropProbability.getDoubleValue();
        if (RandomUtil.rollChance(self.worldObj.rand, probability)) {
            self.dropItem(UBItems.lucky_clover);
        }
    }
}