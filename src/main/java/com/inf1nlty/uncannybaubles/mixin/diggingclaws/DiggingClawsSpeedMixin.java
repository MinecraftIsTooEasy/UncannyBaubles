package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import baubles.api.BaubleSlotHelper;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.DiggingClawsUtil;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class DiggingClawsSpeedMixin {

    @Unique
    private static final float UB_DIGGING_CLAWS_BASE_SPEED_SINGLE = 10.0F;

    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At("RETURN"), cancellable = true)
    private void ub$diggingClawsStoneSpeed(int x, int y, int z, boolean apply_held_item, CallbackInfoReturnable<Float> cir)
    {
        if (cir.getReturnValue() > 0.0f) return;

        EntityPlayer player = (EntityPlayer) (Object) this;

        if (player.getHeldItem() != null) return;

        int count = BaubleSlotHelper.countHandsOfType(player, UBItems.digging_claws);

        if (count <= 0) return;

        Block block = Block.blocksList[player.worldObj.getBlockId(x, y, z)];

        if (block == null) return;

        int metadata = player.worldObj.getBlockMetadata(x, y, z);

        if (!DiggingClawsUtil.isValidTarget(block, metadata)) return;

        if (player.worldObj.getBlockHardness(x, y, z) <= 0.0F) return;

        float strVsBlock = UB_DIGGING_CLAWS_BASE_SPEED_SINGLE * count;

        if (block == Block.web)
        {
            strVsBlock *= 0.2F;
        }

        if (player.isPotionActive(Potion.digSpeed))
        {
            strVsBlock *= 1.0F + (float)(player.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
        }

        if (player.isPotionActive(Potion.digSlowdown))
        {
            strVsBlock *= 1.0F - (float)(player.getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
        }

        if (player.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(player))
        {
            strVsBlock /= 5.0F;
        }

        if (!player.onGround)
        {
            strVsBlock /= 5.0F;
        }

        if (!player.hasFoodEnergy())
        {
            strVsBlock /= 5.0F;
        }

        strVsBlock *= 1.0F + player.getLevelModifier(EnumLevelBonus.HARVESTING);

        cir.setReturnValue(strVsBlock);
    }
}
