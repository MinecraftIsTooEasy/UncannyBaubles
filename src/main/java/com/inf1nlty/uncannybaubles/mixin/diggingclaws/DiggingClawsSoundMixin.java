package com.inf1nlty.uncannybaubles.mixin.diggingclaws;

import baubles.api.BaubleSlotHelper;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.DiggingClawsUtil;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerControllerMP.class)
public abstract class DiggingClawsSoundMixin {

    @Final @Shadow private Minecraft mc;

    @Redirect(method = "onPlayerDamageBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/SoundManager;playSound(Ljava/lang/String;FFFFF)V"))
    private void ub$replaceDigSound(SoundManager soundManager, String soundName, float x, float y, float z, float volume, float pitch)
    {
        if (mc.thePlayer.getHeldItem() == null && BaubleSlotHelper.countHandsOfType(mc.thePlayer, UBItems.digging_claws) > 0)
        {
            int blockX = (int)(x - 0.5F);
            int blockY = (int)(y - 0.5F);
            int blockZ = (int)(z - 0.5F);
            Block block = mc.theWorld.getBlock(blockX, blockY, blockZ);
            int metadata = mc.theWorld.getBlockMetadata(blockX, blockY, blockZ);

            if (DiggingClawsUtil.isValidTarget(block, metadata))
            {
                soundManager.playSound(UBSounds.digging_claws_dig.toString(), x, y, z, 0.3F, 1.0F);
                return;
            }
        }

        soundManager.playSound(soundName, x, y, z, volume, pitch);
    }
}
