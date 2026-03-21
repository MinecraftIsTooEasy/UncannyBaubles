package com.inf1nlty.uncannybaubles.effect.jump;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public final class CloudDoubleJumpEffect {

    private static final Map<EntityLivingBase, JumpState> STATES = Collections.synchronizedMap(new WeakHashMap<>());

    private static final class JumpState {
        private boolean jumpingByCloud;
        private boolean hasDoubleJumped;
        private boolean lastJumping;
        private boolean jumpPressedThisTick;
    }

    private static JumpState getState(EntityLivingBase entity) {
        JumpState state = STATES.get(entity);

        if (state == null) {
            state = new JumpState();
            STATES.put(entity, state);
        }

        return state;
    }

    public static void onLivingUpdateHead(EntityLivingBase entity, boolean isJumping) {
        JumpState state = getState(entity);
        state.jumpPressedThisTick = isJumping && !state.lastJumping;
        state.lastJumping = isJumping;
    }

    public static void onUpdateFallStateHead(EntityLivingBase entity, boolean onGround) {
        if (!onGround) return;

        JumpState state = getState(entity);
        state.hasDoubleJumped = false;
        state.jumpingByCloud = false;
    }

    public static float modifyFallDistance(EntityLivingBase entity, float distance) {
        if (!canDoubleJump(entity)) {
            return distance;
        }

        return Math.max(distance - 2.5F, 0.0F);
    }

    public static boolean modifyOnGroundRead(EntityLivingBase entity, boolean original) {
        JumpState state = getState(entity);

        if (original) {
            state.jumpingByCloud = false;
            return true;
        }

        if (!canDoubleJump(entity)) {
            state.jumpingByCloud = false;
            return false;
        }

        if (state.hasDoubleJumped) {
            return false;
        }

        if (!state.jumpPressedThisTick) {
            return false;
        }

        state.jumpingByCloud = true;
        return true;
    }

    public static void onJumpInvoked(EntityLivingBase entity) {
        JumpState state = getState(entity);
        if (!state.jumpingByCloud) return;

        state.hasDoubleJumped = true;
        state.jumpingByCloud = false;
        entity.fallDistance = 0.0F;

        if (entity instanceof EntityPlayer player) {
            player.playSound(UBSounds.double_jump.toString(), 1.0F, 1.0F);
        }
    }

    private static boolean canDoubleJump(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return false;
        return BalloonEffectHelper.hasCloudDoubleJump(player);
    }
}
