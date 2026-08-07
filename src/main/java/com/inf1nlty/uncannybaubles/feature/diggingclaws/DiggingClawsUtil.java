package com.inf1nlty.uncannybaubles.feature.diggingclaws;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.Block;
import net.minecraft.BlockOre;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumLevelBonus;
import net.minecraft.Material;
import net.minecraft.Potion;

public final class DiggingClawsUtil {

    public static final int DIGGING_CLAWS_HARVEST_LEVEL = 2;
    private static final float BASE_SPEED_SINGLE = 10.0F;

    public static boolean isValidTarget(Block block, int metadata) {
        return block != null
            && block.blockMaterial == Material.stone
            && !(block instanceof BlockOre)
            && block.getMinHarvestLevel(metadata) <= DIGGING_CLAWS_HARVEST_LEVEL;
    }

    public static float getBareHandStoneSpeed(EntityPlayer player, int x, int y, int z, float originalSpeed) {
        if (originalSpeed > 0.0f) return originalSpeed;
        if (player.getHeldItem() != null) return originalSpeed;

        int count = BaubleSlotHelper.countHandsOfType(player, UBItems.digging_claws);
        if (count <= 0) return originalSpeed;

        Block block = Block.blocksList[player.worldObj.getBlockId(x, y, z)];
        if (block == null) return originalSpeed;

        int metadata = player.worldObj.getBlockMetadata(x, y, z);
        if (!isValidTarget(block, metadata)) return originalSpeed;
        if (player.worldObj.getBlockHardness(x, y, z) <= 0.0F) return originalSpeed;

        float speed = BASE_SPEED_SINGLE * count;

        if (block == Block.web) {
            speed *= 0.2F;
        }

        if (player.isPotionActive(Potion.digSpeed)) {
            speed *= 1.0F + (float)(player.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
        }

        if (player.isPotionActive(Potion.digSlowdown)) {
            speed *= 1.0F - (float)(player.getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
        }

        if (player.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(player)) {
            speed /= 5.0F;
        }

        if (!player.onGround) {
            speed /= 5.0F;
        }

        if (!player.hasFoodEnergy()) {
            speed /= 5.0F;
        }

        return speed * (1.0F + player.getLevelModifier(EnumLevelBonus.HARVESTING));
    }

    public static boolean hasEquipped(EntityPlayer player) {
        return BaubleSlotHelper.countHandsOfType(player, UBItems.digging_claws) > 0;
    }
}
