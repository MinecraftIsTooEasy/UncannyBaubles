package com.inf1nlty.uncannybaubles.mixin;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class EntityPlayerResistantMixin extends Entity {

    public EntityPlayerResistantMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public int maxHurtResistantTime;

    @Inject(method = "attackEntityFrom", at = @At("RETURN"))
    private void ub$adjustPlayerHurtResistance(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {

        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if (!(self instanceof EntityPlayer player)) return;

        if (player.worldObj == null || player.worldObj.isRemote) return;

        if (damage == null) return;

        if (UBItems.cross_necklace == null) return;
        if (!BaubleSlotHelper.hasAmuletOfType(player, UBItems.cross_necklace)) return;

        if (this.hurtResistantTime == this.maxHurtResistantTime) {
            this.hurtResistantTime = 40;
        }
    }
}