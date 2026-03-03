package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import com.inf1nlty.uncannybaubles.UBConfigs;
import com.inf1nlty.uncannybaubles.item.UBItems;
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

    @Unique private Material ub$harvestedBlockMaterial = null;

    @Inject(method = "tryHarvestBlock", at = @At("HEAD"))
    private void ub$captureBlockMaterial(int x, int y, int z, CallbackInfoReturnable<Boolean> cir)
    {
        Block block = theWorld.getBlock(x, y, z);
        ub$harvestedBlockMaterial = (block != null) ? block.blockMaterial : null;
    }

    @Inject(method = "tryHarvestBlock", at = @At("RETURN"))
    private void ub$dropDiggingClawsFromStone(int x, int y, int z, CallbackInfoReturnable<Boolean> cir)
    {
        try {
            if (!cir.getReturnValue()) return;

            if (theWorld.isRemote) return;

            if (ub$harvestedBlockMaterial != Material.stone) return;

            double prob = UBConfigs.diggingClawsStoneDropProbability.getDoubleValue();

            if (prob <= 0.0) return;

            if (theWorld.rand.nextDouble() < prob)
            {
                EntityItem drop = new EntityItem(theWorld, x + 0.5, y + 0.5, z + 0.5, new ItemStack(UBItems.digging_claws, 1));
                drop.delayBeforeCanPickup = 10;
                theWorld.spawnEntityInWorld(drop);
            }
        }
        finally
        {
            ub$harvestedBlockMaterial = null;
        }
    }
}