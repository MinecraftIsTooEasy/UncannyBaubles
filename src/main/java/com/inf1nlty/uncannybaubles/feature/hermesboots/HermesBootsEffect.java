package com.inf1nlty.uncannybaubles.feature.hermesboots;

import baubles.api.BaubleSlotHelper;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public final class HermesBootsEffect {

    private static final int MAX_TICKS = 60;
    private static final int DECAY_TICKS = 4;
    private static final double MAX_BOOST = 1.0;
    private static final int SOUND_THRESHOLD = (int) (MAX_TICKS * 0.8);
    private static final Map<EntityLivingBase, HermesBootsState> STATES = Collections.synchronizedMap(new WeakHashMap<>());

    public static void update(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return;

        if (isWearing(player)) {
            player.stepHeight = Math.max(player.stepHeight, 1.0F);

            HermesBootsState state = getState(entity);
            int moveProgress = state.moveProgress;
            boolean sprinting = player.isSprinting() && player.onGround;
            if (sprinting) {
                moveProgress = Math.min(MAX_TICKS, moveProgress + 1);
            } else {
                moveProgress = Math.max(0, moveProgress - DECAY_TICKS);
                state.soundTimer = 0;
            }

            state.moveProgress = moveProgress;
            playRunSoundIfReady(player, state, sprinting);
            return;
        }

        getState(entity).reset();
        if (player.stepHeight >= 1.0F) player.stepHeight = 0.5F;
    }

    public static float modifyMoveSpeed(EntityLivingBase entity, float baseSpeed) {
        if (!(entity instanceof EntityPlayer player)) return baseSpeed;
        if (!player.isSprinting()) return baseSpeed;

        int moveProgress = getState(entity).moveProgress;
        if (moveProgress <= 0) return baseSpeed;

        if (isWearing(player)) {
            float boost = (float) ((double) moveProgress / MAX_TICKS * MAX_BOOST);
            return baseSpeed * (1.0f + boost);
        }

        return baseSpeed;
    }

    private static HermesBootsState getState(EntityLivingBase entity) {
        return STATES.computeIfAbsent(entity, ignored -> new HermesBootsState());
    }

    private static boolean isWearing(EntityPlayer player) {
        return BaubleSlotHelper.hasFeetOfType(player, UBItems.hermes_boots);
    }

    private static void playRunSoundIfReady(EntityPlayer player, HermesBootsState state, boolean sprinting) {
        if (!sprinting || state.moveProgress < SOUND_THRESHOLD || player.worldObj.isRemote) {
            return;
        }

        state.soundTimer++;
        if (state.soundTimer >= 4) {
            state.soundTimer = 0;
            player.worldObj.playSoundAtEntity(
                player,
                UBSounds.hermes_boots_run.toString(),
                0.35F,
                0.9F + player.worldObj.rand.nextFloat() * 0.2F
            );
        }
    }
}
