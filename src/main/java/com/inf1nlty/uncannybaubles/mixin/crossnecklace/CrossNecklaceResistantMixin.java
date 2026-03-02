package com.inf1nlty.uncannybaubles.mixin.crossnecklace;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class CrossNecklaceResistantMixin extends Entity {

    public CrossNecklaceResistantMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public int maxHurtResistantTime;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$onUpdate(CallbackInfo ci) {

        Object self = this;

        if (!(self instanceof EntityPlayer player)) return;

        if (player.worldObj == null || player.worldObj.isRemote) return;

        boolean wearing = UBItems.cross_necklace != null && BaubleSlotHelper.hasAmuletOfType(player, UBItems.cross_necklace);
        if (wearing)
        {
            this.maxHurtResistantTime = 40;
        }
        else
        {
            this.maxHurtResistantTime = 20;
        }
    }
}