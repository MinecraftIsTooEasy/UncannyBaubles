package com.inf1nlty.uncannybaubles.network;

public enum EnumPlayerStateType {

    LAVA_IMMUNITY(0),
    ETERNAL_BEEF_COOLDOWN(1),
    FIERCE_KITTY_SLIPPERS_COOLDOWN(2),
    BROKEN_ANKH_COOLDOWN(3);

    private final int id;

    EnumPlayerStateType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EnumPlayerStateType byId(int id) {
        for (EnumPlayerStateType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
