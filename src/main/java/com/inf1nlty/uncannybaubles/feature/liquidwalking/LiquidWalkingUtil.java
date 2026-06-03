package com.inf1nlty.uncannybaubles.feature.liquidwalking;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.Block;
import net.minecraft.EntityPlayer;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.World;

public final class LiquidWalkingUtil {

    public static void applyWaterWalker(EntityPlayer player) {
        if (!BaubleSlotHelper.hasFeetOfType(player, UBItems.water_walking_boots)) return;
        apply(player, Material.water);
    }

    public static void applyLavaWalker(EntityPlayer player) {
        if (!BaubleSlotHelper.hasFeetOfType(player, UBItems.lava_walking_boots)) return;
        apply(player, Material.lava, Material.water);
    }

    public static void apply(EntityPlayer player, Material... materials) {
        if (player.isSneaking()) return;
        if (player.capabilities.isFlying) return;

        int x = player.getBlockPosX();
        int z = player.getBlockPosZ();
        int startY = MathHelper.floor_double(player.getFootPosY());

        Surface surface = findFirstSurface(player.worldObj, x, startY, z, materials);
        if (surface == null) return;

        Block surfaceBlock = player.worldObj.getBlock(x, surface.y, z);
        if (surfaceBlock == null || surfaceBlock.blockMaterial != surface.material) return;

        int meta = player.worldObj.getBlockMetadata(x, surface.y, z);
        if (meta >= 8) meta = 0;

        double liquidHeight = 1.0D - (meta / 8.0D);
        double targetFootY = surface.y + liquidHeight + 0.01D;
        double distanceToSurface = player.getFootPosY() - targetFootY;

        if (distanceToSurface > 1.5D) return;
        if (player.motionY > 0.42D) return;

        double lockLowerBound = -0.35D;
        double lockUpperBound = 0.15D;

        if (distanceToSurface < lockLowerBound || distanceToSurface > lockUpperBound) {
            return;
        }

        if (Math.abs(distanceToSurface) > 0.01D) {
            player.setPosition(player.posX, toPosY(player, targetFootY), player.posZ);
        }

        if (player.motionY < 0.0D) {
            player.motionY = 0.0D;
        }

        player.onGround = true;
        player.fallDistance = 0.0F;
        player.stepHeight = 0.6F;
        updateCameraBobbing(player);
    }

    private static Surface findFirstSurface(World world, int x, int startY, int z, Material... materials) {
        for (Material material : materials) {
            int y = findSurface(world, x, startY, z, material);
            if (y >= 0) {
                return new Surface(y, material);
            }
        }

        return null;
    }

    private static int findSurface(World world, int x, int startY, int z, Material material) {
        int maxHeight = world.getHeight();

        for (int dy = 0; dy <= 6; dy++) {
            int checkY = startY - dy;
            if (checkY < 0) break;

            if (isSurface(world, x, checkY, z, material, maxHeight)) {
                return checkY;
            }
        }

        for (int dy = 1; dy <= 6; dy++) {
            int checkY = startY + dy;
            if (checkY >= maxHeight) break;

            if (isSurface(world, x, checkY, z, material, maxHeight)) {
                return checkY;
            }
        }

        return -1;
    }

    private static boolean isSurface(World world, int x, int y, int z, Material material, int maxHeight) {
        Block block = world.getBlock(x, y, z);
        if (block == null || block.blockMaterial != material) return false;

        Block blockAbove = y + 1 < maxHeight ? world.getBlock(x, y + 1, z) : null;
        return blockAbove == null || blockAbove.blockMaterial != material;
    }

    private static double toPosY(EntityPlayer player, double footY) {
        return footY + (double) player.yOffset - (double) player.ySize;
    }

    private static void updateCameraBobbing(EntityPlayer player) {
        float horizontalSpeed = MathHelper.sqrt_double(player.motionX * player.motionX + player.motionZ * player.motionZ);

        if (horizontalSpeed > 0.1F) {
            horizontalSpeed = 0.1F;
        }

        player.cameraYaw += (horizontalSpeed - player.cameraYaw) * 0.4F;
    }

    private record Surface(int y, Material material) {
    }
}
