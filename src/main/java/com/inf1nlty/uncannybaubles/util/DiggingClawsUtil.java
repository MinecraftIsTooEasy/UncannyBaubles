package com.inf1nlty.uncannybaubles.util;

import net.minecraft.Block;
import net.minecraft.BlockOre;
import net.minecraft.Material;

public final class DiggingClawsUtil {

    private DiggingClawsUtil() {}

    public static final int DIGGING_CLAWS_HARVEST_LEVEL = 2;

    public static boolean isValidTarget(Block block, int metadata) {
        return block != null
            && block.blockMaterial == Material.stone
            && !(block instanceof BlockOre)
            && block.getMinHarvestLevel(metadata) <= DIGGING_CLAWS_HARVEST_LEVEL;
    }
}
