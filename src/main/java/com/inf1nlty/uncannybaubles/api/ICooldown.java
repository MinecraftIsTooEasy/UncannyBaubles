package com.inf1nlty.uncannybaubles.api;

public interface ICooldown {

    static ICooldown of(Object player) {
        if (player instanceof ICooldown cooldown) {
            return cooldown;
        }
        throw new IllegalArgumentException("Object does not implement ICooldown");
    }

    static int get(Object player) {
        if (player instanceof ICooldown cooldown) {
            return cooldown.ub$getEternalBeefCooldown();
        }
        return 0;
    }

    static void set(Object player, int ticks) {
        if (player instanceof ICooldown cooldown) {
            cooldown.ub$setEternalBeefCooldown(ticks);
        }
    }

    static void setRaw(Object player, int ticks) {
        if (player instanceof ICooldown cooldown) {
            cooldown.ub$setEternalBeefCooldownRaw(ticks);
        }
    }

    int ub$getEternalBeefCooldown();

    void ub$setEternalBeefCooldown(int ticks);

    void ub$setEternalBeefCooldownRaw(int ticks);
}
