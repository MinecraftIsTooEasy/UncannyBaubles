package com.inf1nlty.uncannybaubles.mixin.hermes;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class HermesBootsPlayerCapabilitiesMixin {


    @Unique private int ub$moveProgress = 0;

    @Unique private static final int MAX_TICKS = 60;
    @Unique private static final int DECAY_TICKS = 4;
    @Unique private static final double MAX_BOOST = 1.0;

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void ub$hermesUpdate(CallbackInfo ci) {

        EntityLivingBase self = (EntityLivingBase) (Object) this;

        if (!(self instanceof EntityPlayer player)) return;

        if (player.worldObj == null) return;

        boolean wearing = UBItems.hermes_boots != null && BaubleSlotHelper.hasFeetOfType(player, UBItems.hermes_boots);

        if (!wearing)
        {
            ub$moveProgress = 0;
            if (player.stepHeight >= 1.0F) player.stepHeight = 0.5F;
            return;
        }

        player.stepHeight = Math.max(player.stepHeight, 1.0F);

        boolean pressingMove = player.isSprinting() && player.onGround;

        if (pressingMove)
        {
            if (ub$moveProgress < MAX_TICKS) ub$moveProgress++;
        }
        else
        {
            ub$moveProgress = Math.max(0, ub$moveProgress - DECAY_TICKS);
        }
    }

    @Redirect(method = "moveEntityWithHeading", at = @At(value  = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;getAIMoveSpeed()F"))
    private float ub$hermesBoostAIMoveSpeed(EntityLivingBase instance)
    {
        float base = instance.getAIMoveSpeed();

        if (ub$moveProgress <= 0) return base;

        if (!(instance instanceof EntityPlayer player)) return base;

        if (!player.isSprinting()) return base;

        if (UBItems.hermes_boots == null || !BaubleSlotHelper.hasFeetOfType(player, UBItems.hermes_boots)) return base;

        float boost = (float) ((double) ub$moveProgress / MAX_TICKS * MAX_BOOST);

        return base * (1.0f + boost);
    }
}
