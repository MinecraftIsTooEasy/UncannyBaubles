package com.inf1nlty.uncannybaubles.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private static final long PROBABILITY_SCALE = 1_000_000_000L;
    private static final long NORMALIZE_PROGRESS = PROBABILITY_SCALE * 4096L;
    private static final Map<Random, Map<Long, RandomRollingState>> ROLLING_STATES = new WeakHashMap<>();

    public static boolean rollChance(Random rand, double probability) {
        if (!Double.isFinite(probability) || probability <= 0.0D) return false;
        if (probability >= 1.0D) return true;

        Random random = (rand != null) ? rand : ThreadLocalRandom.current();
        long threshold = Math.round(probability * PROBABILITY_SCALE);

        if (threshold <= 0L) {
            return random.nextDouble() < probability;
        }

        if (threshold >= PROBABILITY_SCALE) {
            return true;
        }

        RandomRollingState state = getRollingState(random, threshold);
        synchronized (state) {
            if (state.nextTrigger < 0L) {
                state.nextTrigger = nextLongBounded(random, PROBABILITY_SCALE);
            }

            state.progress += threshold;
            boolean hit = state.progress >= state.nextTrigger;

            if (hit) {
                state.nextTrigger += PROBABILITY_SCALE;
            }

            if (state.progress >= NORMALIZE_PROGRESS) {
                long offset = state.progress / PROBABILITY_SCALE - 1L;

                if (offset > 0L) {
                    long delta = offset * PROBABILITY_SCALE;
                    state.progress -= delta;
                    state.nextTrigger -= delta;
                }
            }

            return hit;
        }
    }

    private static RandomRollingState getRollingState(Random random, long threshold) {
        synchronized (ROLLING_STATES) {
            return ROLLING_STATES
                .computeIfAbsent(random, ignored -> new HashMap<>())
                .computeIfAbsent(threshold, ignored -> new RandomRollingState());
        }
    }

    private static long nextLongBounded(Random rand, long bound) {
        if (bound <= 0L) throw new IllegalArgumentException("bound must be positive");

        long mask = bound - 1L;

        if ((bound & mask) == 0L) {
            return rand.nextLong() & mask;
        }

        long raw = rand.nextLong() >>> 1;
        long value = raw % bound;

        while (raw + mask - value < 0L) {
            raw = rand.nextLong() >>> 1;
            value = raw % bound;
        }

        return value;
    }
}
