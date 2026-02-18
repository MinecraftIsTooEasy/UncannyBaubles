package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class LavaWalkerEntityPlayerMixin {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$lavaWalk(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player == null || player.worldObj == null) return;
        if (!ub$canWalkOnLava(player)) return;
        if (player.isSneaking()) return;

        int x = player.getBlockPosX();
        int z = player.getBlockPosZ();
        int startY = MathHelper.floor_double(player.posY);

        int surfaceY = ub$findLavaSurface(player.worldObj, x, startY, z);
        Material surfaceMaterial = Material.lava;

        if (surfaceY < 0) {
            surfaceY = ub$findWaterSurface(player.worldObj, x, startY, z);
            surfaceMaterial = Material.water;
        }

        if (surfaceY < 0) return;

        Block surfaceBlock = player.worldObj.getBlock(x, surfaceY, z);
        if (surfaceBlock == null || surfaceBlock.blockMaterial != surfaceMaterial) return;

        int meta = player.worldObj.getBlockMetadata(x, surfaceY, z);
        if (meta >= 8) meta = 0;

        double liquidHeight = 1.0D - (meta / 8.0D);
        double targetY = surfaceY + liquidHeight + 0.08D;
        double distanceToSurface = player.posY - targetY;

        if (distanceToSurface > 1.5D) return;
        if (player.motionY > 0.42D) return;

        if (distanceToSurface < -0.05D) {
            player.posY = targetY;
        }

        player.motionY = 0.0D;
        player.onGround = true;
        player.fallDistance = 0.0F;
        player.stepHeight = 0.6F;
    }

    @Unique
    private boolean ub$canWalkOnLava(EntityPlayer player) {
        if (UBItems.lava_walking_boots == null) return false;
        if (!BaublesUtil.hasBaubleWorn(player, UBItems.lava_walking_boots)) return false;
        return player.capabilities == null || !player.capabilities.isFlying;
    }

    @Unique
    private int ub$findLavaSurface(World world, int x, int startY, int z) {
        int maxHeight = world.getHeight();

        for (int dy = 0; dy <= 6; dy++) {
            int checkY = startY - dy;
            if (checkY < 0) break;

            Block block = world.getBlock(x, checkY, z);
            if (block != null && block.blockMaterial == Material.lava) {
                Block blockAbove = checkY + 1 < maxHeight ? world.getBlock(x, checkY + 1, z) : null;
                if (blockAbove == null || blockAbove.blockMaterial != Material.lava) {
                    return checkY;
                }
            }
        }

        for (int dy = 1; dy <= 6; dy++) {
            int checkY = startY + dy;
            if (checkY >= maxHeight) break;

            Block block = world.getBlock(x, checkY, z);
            if (block != null && block.blockMaterial == Material.lava) {
                Block blockAbove = checkY + 1 < maxHeight ? world.getBlock(x, checkY + 1, z) : null;
                if (blockAbove == null || blockAbove.blockMaterial != Material.lava) {
                    return checkY;
                }
            }
        }

        return -1;
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
}
