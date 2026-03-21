package com.inf1nlty.uncannybaubles.mixin.waterwalker;

import com.inf1nlty.uncannybaubles.item.UBItems;
import baubles.api.BaubleSlotHelper;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class WaterWalkerEntityPlayerMixin {

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void ub$waterWalk(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player == null || player.worldObj == null) return;
        if (!ub$canWalkOnWater(player)) return;
        if (player.isSneaking()) return;

        int x = player.getBlockPosX();
        int z = player.getBlockPosZ();
        int startY = MathHelper.floor_double(player.getFootPosY());

        int waterSurfaceY = ub$findWaterSurface(player.worldObj, x, startY, z);
        if (waterSurfaceY < 0) return;

        Block surfaceBlock = player.worldObj.getBlock(x, waterSurfaceY, z);
        if (surfaceBlock == null || surfaceBlock.blockMaterial != Material.water) return;

        int meta = player.worldObj.getBlockMetadata(x, waterSurfaceY, z);
        if (meta >= 8) meta = 0;

        double waterHeight = 1.0D - (meta / 8.0D);
        double targetFootY = waterSurfaceY + waterHeight + 0.01D;
        double distanceToSurface = player.getFootPosY() - targetFootY;

        if (distanceToSurface > 1.5D) return;
        if (player.motionY > 0.42D) return;

        double lockLowerBound = -0.35D;
        double lockUpperBound = 0.15D;

        if (distanceToSurface < lockLowerBound || distanceToSurface > lockUpperBound) {
            return;
        }

        if (Math.abs(distanceToSurface) > 0.01D) {
            player.setPosition(player.posX, ub$toPosY(player, targetFootY), player.posZ);
        }

        if (player.motionY < 0.0D) {
            player.motionY = 0.0D;
        }

        player.onGround = true;
        player.fallDistance = 0.0F;
        player.stepHeight = 0.6F;
        ub$updateCameraBobbing(player);
    }

    @Unique
    private boolean ub$canWalkOnWater(EntityPlayer player) {
        if (UBItems.water_walking_boots == null) return false;
        if (!BaubleSlotHelper.hasFeetOfType(player, UBItems.water_walking_boots)) return false;
        return player.capabilities == null || !player.capabilities.isFlying;
    }

    @Unique
    private int ub$findWaterSurface(World world, int x, int startY, int z) {
        int maxHeight = world.getHeight();

        for (int dy = 0; dy <= 6; dy++) {
            int checkY = startY - dy;
            if (checkY < 0) break;

            Block block = world.getBlock(x, checkY, z);
            if (block != null && block.blockMaterial == Material.water) {
                Block blockAbove = checkY + 1 < maxHeight ? world.getBlock(x, checkY + 1, z) : null;
                if (blockAbove == null || blockAbove.blockMaterial != Material.water) {
                    return checkY;
                }
            }
        }

        for (int dy = 1; dy <= 6; dy++) {
            int checkY = startY + dy;
            if (checkY >= maxHeight) break;

            Block block = world.getBlock(x, checkY, z);
            if (block != null && block.blockMaterial == Material.water) {
                Block blockAbove = checkY + 1 < maxHeight ? world.getBlock(x, checkY + 1, z) : null;
                if (blockAbove == null || blockAbove.blockMaterial != Material.water) {
                    return checkY;
                }
            }
        }

        return -1;
    }

    @Unique
    private double ub$toPosY(EntityPlayer player, double footY) {
        return footY + (double)player.yOffset - (double)player.ySize;
    }

    @Unique
    private void ub$updateCameraBobbing(EntityPlayer player) {
        float horizontalSpeed = MathHelper.sqrt_double(player.motionX * player.motionX + player.motionZ * player.motionZ);

        if (horizontalSpeed > 0.1F) {
            horizontalSpeed = 0.1F;
        }

        player.cameraYaw += (horizontalSpeed - player.cameraYaw) * 0.4F;
    }
}
