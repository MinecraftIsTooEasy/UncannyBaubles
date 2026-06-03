package com.inf1nlty.uncannybaubles.api;

public interface IFierceKittySlippersCooldown {

    static IFierceKittySlippersCooldown of(Object player) {
        if (player instanceof IFierceKittySlippersCooldown cooldown) {
            return cooldown;
        }
        throw new IllegalArgumentException("Object does not implement IFierceKittySlippersCooldown");
    }

    static int get(Object player) {
        if (player instanceof IFierceKittySlippersCooldown cooldown) {
            return cooldown.ub$getFierceKittySlippersCooldown();
        }
        return 0;
    }

    static void set(Object player, int ticks) {
        if (player instanceof IFierceKittySlippersCooldown cooldown) {
            cooldown.ub$setFierceKittySlippersCooldown(ticks);
        }
    }

    static void setRaw(Object player, int ticks) {
        if (player instanceof IFierceKittySlippersCooldown cooldown) {
            cooldown.ub$setFierceKittySlippersCooldownRaw(ticks);
        }
    }

    int ub$getFierceKittySlippersCooldown();

    void ub$setFierceKittySlippersCooldown(int ticks);

    void ub$setFierceKittySlippersCooldownRaw(int ticks);
}
