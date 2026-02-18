package com.inf1nlty.uncannybaubles.ai;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;

public class EntityAIAvoidPlayerWithFierceKittySlippers extends EntityAIBase {

    private final EntityCreeper creeper;
    private final float avoidDistance;
    private final double farSpeed;
    private final double nearSpeed;
    private EntityPlayer targetPlayer;
    private PathEntity escapePath;
    private final PathNavigate navigator;
    private boolean hasTriggeredSideEffect = false;

    public EntityAIAvoidPlayerWithFierceKittySlippers(EntityCreeper creeper, float avoidDistance, double farSpeed, double nearSpeed) {
        this.creeper = creeper;
        this.avoidDistance = avoidDistance;
        this.farSpeed = farSpeed;
        this.nearSpeed = nearSpeed;
        this.navigator = creeper.getNavigator();
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        EntityPlayer nearestPlayer = this.creeper.worldObj.getClosestPlayerToEntity(this.creeper, this.avoidDistance, false);

        if (nearestPlayer == null) {
            return false;
        }

        if (!BaublesUtil.hasBaubleWorn(nearestPlayer, UBItems.fierce_kitty_slippers)) {
            return false;
        }

        IFierceKittySlippersCooldown cooldownData = (IFierceKittySlippersCooldown) nearestPlayer;
        if (cooldownData.ub$getFierceKittySlippersCooldown() > 0) {
            return false;
        }

        if (this.creeper.getAttackTarget() == nearestPlayer) {
            this.creeper.setAttackTarget(null);
        }

        this.targetPlayer = nearestPlayer;
        this.hasTriggeredSideEffect = false;

        Vec3 playerVec = this.creeper.worldObj.getWorldVec3Pool().getVecFromPool(
            nearestPlayer.posX,
            nearestPlayer.posY,
            nearestPlayer.posZ
        );

        Vec3 escapeVec = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
            this.creeper,
            16,
            7,
            playerVec);

        if (escapeVec == null) {
            return false;
        }

        double escapeDistSq = nearestPlayer.getDistanceSq(escapeVec.xCoord, escapeVec.yCoord, escapeVec.zCoord);
        double currentDistSq = nearestPlayer.getDistanceSqToEntity(this.creeper);

        if (escapeDistSq < currentDistSq) {
            return false;
        }

        this.escapePath = this.navigator.getPathToXYZ(escapeVec.xCoord, escapeVec.yCoord, escapeVec.zCoord);

        if (this.escapePath == null) {
            return false;
        }

        return this.escapePath.isDestinationSame(escapeVec);
    }

    @Override
    public boolean continueExecuting() {
        if (this.navigator.noPath()) {
            return false;
        }

        if (this.targetPlayer == null || this.targetPlayer.isDead) {
            return false;
        }

        IFierceKittySlippersCooldown cooldownData = (IFierceKittySlippersCooldown) this.targetPlayer;
        if (cooldownData.ub$getFierceKittySlippersCooldown() > 0) {
            return false;
        }

        return BaublesUtil.hasBaubleWorn(this.targetPlayer, UBItems.fierce_kitty_slippers);
    }

    @Override
    public void startExecuting() {
        this.navigator.setPath(this.escapePath, this.farSpeed);

        if (this.creeper.getAttackTarget() == this.targetPlayer) {
            this.creeper.setAttackTarget(null);
        }

        if (this.creeper.getCreeperState() > 0) {
            this.creeper.setCreeperState(-1);
        }

        if (!this.hasTriggeredSideEffect && this.creeper.getRNG().nextFloat() < 0.1F) {
            triggerSideEffect();
            this.hasTriggeredSideEffect = true;
        }
    }

    private void triggerSideEffect() {
        if (this.targetPlayer == null || this.targetPlayer.worldObj.isRemote) {
            return;
        }

        this.targetPlayer.attackEntityFrom(new Damage(DamageSource.magic, 1.0F));

        IFierceKittySlippersCooldown cooldownData = (IFierceKittySlippersCooldown) this.targetPlayer;
        cooldownData.ub$setFierceKittySlippersCooldown(6000);

        this.targetPlayer.worldObj.playSoundAtEntity(
            this.targetPlayer,
            UBSounds.fierce_kitty_slippers.toString(),
            1.0F,
            1.0F
        );
    }

    @Override
    public void resetTask() {
        this.targetPlayer = null;
        this.hasTriggeredSideEffect = false;
    }

    @Override
    public void updateTask() {
        if (this.targetPlayer == null) {
            return;
        }

        double distanceSq = this.creeper.getDistanceSqToEntity(this.targetPlayer);

        if (distanceSq < 49.0D) {
            this.navigator.setSpeed(this.nearSpeed);
        } else {
            this.navigator.setSpeed(this.farSpeed);
        }

        if (this.creeper.getAttackTarget() == this.targetPlayer) {
            this.creeper.setAttackTarget(null);
        }

        if (this.creeper.getCreeperState() > 0) {
            this.creeper.setCreeperState(-1);
        }
    }
}
