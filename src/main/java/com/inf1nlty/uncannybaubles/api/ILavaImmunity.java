package com.inf1nlty.uncannybaubles.api;

public interface ILavaImmunity {

    static ILavaImmunity of(Object player) {
        if (player instanceof ILavaImmunity lavaImmunity) {
            return lavaImmunity;
        }
        throw new IllegalArgumentException("Object does not implement ILavaImmunity");
    }

    static int getTicks(Object player) {
        if (player instanceof ILavaImmunity lavaImmunity) {
            return lavaImmunity.ub$getLavaImmunityTicks();
        }
        return 0;
    }

    static void setTicks(Object player, int ticks) {
        if (player instanceof ILavaImmunity lavaImmunity) {
            lavaImmunity.ub$setLavaImmunityTicks(ticks);
        }
    }

    int ub$getLavaImmunityTicks();

    void ub$setLavaImmunityTicks(int ticks);
}
