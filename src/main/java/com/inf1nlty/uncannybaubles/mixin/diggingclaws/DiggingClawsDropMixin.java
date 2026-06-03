package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import com.inf1nlty.uncannybaubles.feature.diggingclaws.DiggingClawsUtil;
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
    @Shadow public ServerPlayer thisPlayerMP;

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
            DiggingClawsUtil.tryDropFromHarvestedBlock(thisPlayerMP, theWorld, x, y, z, cir.getReturnValue(), ub$harvestedBlock, ub$harvestedBlockMetadata);
        }
        finally
        {
            ub$harvestedBlock = null;
            ub$harvestedBlockMetadata = 0;
        }
    }
}
