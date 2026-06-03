package com.inf1nlty.uncannybaubles.feature.hermesboots;

final class HermesBootsState {

    int moveProgress;
    int soundTimer;

    void reset() {
        this.moveProgress = 0;
        this.soundTimer = 0;
    }
}
