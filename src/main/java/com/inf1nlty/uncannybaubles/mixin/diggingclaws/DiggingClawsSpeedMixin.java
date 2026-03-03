package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import baubles.api.BaubleSlotHelper;

import com.inf1nlty.uncannybaubles.item.UBItems;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class DiggingClawsSpeedMixin {

    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At("RETURN"), cancellable = true)
    private void ub$diggingClawsStoneSpeed(int x, int y, int z, boolean apply_held_item, CallbackInfoReturnable<Float> cir)
    {
        if (cir.getReturnValue() > 0.0f) return;

        EntityPlayer player = (EntityPlayer) (Object) this;

        if (player.getHeldItem() != null) return;

        int count = BaubleSlotHelper.countHandsOfType(player, UBItems.digging_claws);

        if (count <= 0) return;

        Block block = Block.blocksList[player.worldObj.getBlockId(x, y, z)];

        if (block == null || block.blockMaterial != Material.stone) return;

        if (player.worldObj.getBlockHardness(x, y, z) <= 0.0F) return;

        cir.setReturnValue(count >= 2 ? 20.0F : 10.0F);
    }
}