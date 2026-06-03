package com.inf1nlty.uncannybaubles.feature.jump;

import com.inf1nlty.uncannybaubles.client.UBSounds;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public final class CloudDoubleJumpEffect {

    private static final Map<EntityLivingBase, CloudDoubleJumpState> STATES = Collections.synchronizedMap(new WeakHashMap<>());

    public static void onLivingUpdateHead(EntityLivingBase entity) {
        CloudDoubleJumpState state = getState(entity);
        boolean jumpInput = getJumpInput(entity);
        state.update(entity.onGround, jumpInput);
    }

    public static void onUpdateFallStateHead(EntityLivingBase entity, boolean onGround) {
        if (!onGround || !entity.onGround) return;

        getState(entity).resetGrounded();
    }

    public static float modifyFallDistance(EntityLivingBase entity, float distance) {
        if (canDoubleJump(entity)) {
            return Math.max(distance - 2.5F, 0.0F);
        }

        return distance;
    }

    public static boolean shouldDoubleJump(EntityLivingBase entity) {
        CloudDoubleJumpState state = getState(entity);

        if (entity.worldObj == null || !entity.worldObj.isRemote) return false;
        if (entity.onGround) return false;
        if (!canDoubleJump(entity)) return false;
        if (state.hasDoubleJumped()) return false;
        return state.canConsumeCloudJump();
    }

    public static void onDoubleJumped(EntityLivingBase entity) {
        CloudDoubleJumpState state = getState(entity);
        state.markDoubleJumped();
        entity.fallDistance = 0.0F;

        if (entity instanceof EntityPlayer player) {
            player.playSound(UBSounds.double_jump.toString(), 1.0F, 1.0F);
        }
    }

    private static boolean canDoubleJump(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer player)) return false;
        return BalloonEffectHelper.hasCloudDoubleJump(player);
    }

    private static boolean getJumpInput(EntityLivingBase entity) {
        if (entity.worldObj != null && entity.worldObj.isRemote && entity instanceof EntityPlayer) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer == entity || mc.renderViewEntity == entity) {
                int keyCode = mc.gameSettings.keyBindJump.keyCode;
                if (keyCode < 0) {
                    return mc.gameSettings.keyBindJump.pressed || Mouse.isButtonDown(keyCode + 100);
                }
                return mc.gameSettings.keyBindJump.pressed || Keyboard.isKeyDown(keyCode);
            }
        }

        return false;
    }

    private static CloudDoubleJumpState getState(EntityLivingBase entity) {
        return STATES.computeIfAbsent(entity, ignored -> new CloudDoubleJumpState());
    }
}
