package com.inf1nlty.uncannybaubles.feature.jump;

final class CloudDoubleJumpState {

    private static final int REQUIRED_RELEASE_TICKS = 3;

    private boolean hasDoubleJumped;
    private boolean lastJumping;
    private boolean jumpPressedThisTick;
    private boolean airborne;
    private boolean releasedJumpSinceAirborne;
    private int airborneReleaseTicks;

    void update(boolean onGround, boolean isJumping) {
        this.jumpPressedThisTick = false;

        if (onGround) {
            this.hasDoubleJumped = false;
            this.airborne = false;
            this.releasedJumpSinceAirborne = false;
            this.airborneReleaseTicks = 0;
            this.lastJumping = isJumping;
            return;
        }

        if (!this.airborne) {
            this.airborne = true;
            this.releasedJumpSinceAirborne = !isJumping && !this.lastJumping;
            this.airborneReleaseTicks = this.releasedJumpSinceAirborne ? REQUIRED_RELEASE_TICKS : 0;
        } else if (!isJumping) {
            if (this.airborneReleaseTicks < REQUIRED_RELEASE_TICKS) {
                this.airborneReleaseTicks++;
            }
            if (this.airborneReleaseTicks >= REQUIRED_RELEASE_TICKS) {
                this.releasedJumpSinceAirborne = true;
            }
        } else {
            this.airborneReleaseTicks = 0;
        }

        this.jumpPressedThisTick = isJumping && !this.lastJumping && this.releasedJumpSinceAirborne;
        this.lastJumping = isJumping;
    }

    void resetGrounded() {
        this.hasDoubleJumped = false;
        this.airborne = false;
        this.releasedJumpSinceAirborne = false;
        this.airborneReleaseTicks = 0;
        this.jumpPressedThisTick = false;
    }

    boolean hasDoubleJumped() {
        return this.hasDoubleJumped;
    }

    boolean canConsumeCloudJump() {
        return this.jumpPressedThisTick && !this.hasDoubleJumped;
    }

    void markDoubleJumped() {
        this.hasDoubleJumped = true;
        this.releasedJumpSinceAirborne = false;
        this.airborneReleaseTicks = 0;
        this.jumpPressedThisTick = false;
    }
}
