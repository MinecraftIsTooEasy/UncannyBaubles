package com.inf1nlty.uncannybaubles.api;

public interface IBrokenAnkhCooldown {

    static IBrokenAnkhCooldown of(Object player) {
        if (player instanceof IBrokenAnkhCooldown cooldown) {
            return cooldown;
        }
        throw new IllegalArgumentException("Object does not implement IBrokenAnkhCooldown");
    }

    static int get(Object player) {
        if (player instanceof IBrokenAnkhCooldown cooldown) {
            return cooldown.ub$getBrokenAnkhCooldown();
        }
        return 0;
    }

    static void set(Object player, int ticks) {
        if (player instanceof IBrokenAnkhCooldown cooldown) {
            cooldown.ub$setBrokenAnkhCooldown(ticks);
        }
    }

    static void setRaw(Object player, int ticks) {
        if (player instanceof IBrokenAnkhCooldown cooldown) {
            cooldown.ub$setBrokenAnkhCooldownRaw(ticks);
        }
    }

    int ub$getBrokenAnkhCooldown();

    void ub$setBrokenAnkhCooldown(int ticks);

    void ub$setBrokenAnkhCooldownRaw(int ticks);
}
