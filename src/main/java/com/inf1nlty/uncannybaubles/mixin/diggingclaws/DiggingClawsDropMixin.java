package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.DiggingClawsUtil;
import com.inf1nlty.uncannybaubles.util.RandomUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInWorldManager.class)
public abstract class DiggingClawsDropMixin {

    @Shadow public World theWorld;

    @Unique private Block ub$harvestedBlock = null;
    @Unique private int ub$harvestedBlockMetadata = 0;

    @Inject(method = "tryHarvestBlock", at = @At("HEAD"))
    private void ub$captureBlockMaterial(int x, int y, int z, CallbackInfoReturnable<Boolean> cir)
    {
        ub$harvestedBlock = theWorld.getBlock(x, y, z);
        ub$harvestedBlockMetadata = theWorld.getBlockMetadata(x, y, z);
    }

    @Inject(method = "tryHarvestBlock", at = @At("RETURN"))
    private void ub$dropDiggingClawsFromStone(int x, int y, int z, CallbackInfoReturnable<Boolean> cir)
    {
        try {
            if (!cir.getReturnValue()) return;

            if (theWorld.isRemote) return;

            if (!DiggingClawsUtil.isValidTarget(ub$harvestedBlock, ub$harvestedBlockMetadata)) return;

            double prob = UBConfigs.diggingClawsStoneDropProbability.getDoubleValue();

            if (prob <= 0.0) return;

            if (RandomUtil.rollChance(theWorld.rand, prob))
            {
                EntityItem drop = new EntityItem(theWorld, x + 0.5, y + 0.5, z + 0.5, new ItemStack(UBItems.digging_claws, 1));
                drop.delayBeforeCanPickup = 10;
                theWorld.spawnEntityInWorld(drop);
            }
        }
        finally
        {
            ub$harvestedBlock = null;
            ub$harvestedBlockMetadata = 0;
        }
    }
}
