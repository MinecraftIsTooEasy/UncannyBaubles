package com.inf1nlty.uncannybaubles.feature.lavaimmunity;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.Block;
import net.minecraft.Damage;
import net.minecraft.EntityPlayer;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.World;

public final class LavaImmunityUtil {

    public static final int MAX_TICKS = 140;

    public static int updateTicks(EntityPlayer player, int currentTicks) {
        if (hasLavaImmunityBauble(player)) {
            if (isConsumingImmunity(player)) {
                return Math.max(0, currentTicks - 1);
            }

            return Math.min(MAX_TICKS, currentTicks + 1);
        }

        return 0;
    }

    public static boolean shouldCancelDamage(int ticks, Damage damage) {
        return ticks > 0 && damage != null && (damage.isFireDamage() || damage.isLavaDamage());
    }

    public static boolean hasLavaImmunityBauble(EntityPlayer player) {
        return BaubleSlotHelper.hasCharmOfType(player, UBItems.lava_charm)
            || BaubleSlotHelper.hasFeetOfType(player, UBItems.lava_walking_boots);
    }

    private static boolean isConsumingImmunity(EntityPlayer player) {
        return player.handleLavaMovement() || player.isBurning() || isInFireBlock(player);
    }

    private static boolean isInFireBlock(EntityPlayer player) {
        World world = player.worldObj;
        int minX = MathHelper.floor_double(player.boundingBox.minX);
        int minY = MathHelper.floor_double(player.boundingBox.minY);
        int minZ = MathHelper.floor_double(player.boundingBox.minZ);
        int maxX = MathHelper.floor_double(player.boundingBox.maxX);
        int maxY = MathHelper.floor_double(player.boundingBox.maxY);
        int maxZ = MathHelper.floor_double(player.boundingBox.maxZ);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlock(x, y, z);
                    if (block != null && block.blockMaterial == Material.fire) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
