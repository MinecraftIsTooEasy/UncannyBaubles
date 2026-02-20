package com.inf1nlty.uncannybaubles.util;

import java.util.Random;

public final class RandomUtil {

    private RandomUtil() {}

    public static boolean rollChance(Random rand, double probability) {
        if (probability <= 0.0) return false;
        if (probability >= 1.0) return true;
        if (rand == null) rand = new Random();
        return rand.nextDouble() < probability;
    }
}