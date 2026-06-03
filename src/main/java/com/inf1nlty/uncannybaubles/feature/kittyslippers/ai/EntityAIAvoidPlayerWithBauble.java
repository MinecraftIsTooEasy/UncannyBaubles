package com.inf1nlty.uncannybaubles.feature.kittyslippers.ai;

import net.minecraft.EntityAIBase;
import net.minecraft.EntityCreeper;
import net.minecraft.EntityPlayer;
import net.minecraft.PathEntity;
import net.minecraft.PathNavigate;
import net.minecraft.RandomPositionGenerator;
import net.minecraft.Vec3;

public abstract class EntityAIAvoidPlayerWithBauble extends EntityAIBase {

    protected final EntityCreeper creeper;
    protected EntityPlayer targetPlayer;

    private final float avoidDistance;
    private final double farSpeed;
    private final double nearSpeed;
    private final PathNavigate navigator;
    private PathEntity escapePath;

    protected EntityAIAvoidPlayerWithBauble(EntityCreeper creeper, float avoidDistance, double farSpeed, double nearSpeed) {
        this.creeper = creeper;
        this.avoidDistance = avoidDistance;
        this.farSpeed = farSpeed;
        this.nearSpeed = nearSpeed;
        this.navigator = creeper.getNavigator();
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        EntityPlayer nearestPlayer = this.getClosestPlayer();
        if (nearestPlayer == null || !isValidTarget(nearestPlayer)) {
            return false;
        }

        clearCreeperTarget(nearestPlayer);
        this.targetPlayer = nearestPlayer;
        onTargetAcquired(nearestPlayer);

        Vec3 playerVec = this.creeper.worldObj.getWorldVec3Pool().getVecFromPool(
            nearestPlayer.posX,
            nearestPlayer.posY,
            nearestPlayer.posZ
        );

        Vec3 escapeVec = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
            this.creeper,
            16,
            7,
            playerVec
        );

        if (escapeVec == null) {
            return false;
        }

        double escapeDistSq = nearestPlayer.getDistanceSq(escapeVec.xCoord, escapeVec.yCoord, escapeVec.zCoord);
        double currentDistSq = nearestPlayer.getDistanceSqToEntity(this.creeper);
        if (escapeDistSq < currentDistSq) {
            return false;
        }

        this.escapePath = this.navigator.getPathToXYZ(escapeVec.xCoord, escapeVec.yCoord, escapeVec.zCoord);
        return this.escapePath != null && this.escapePath.isDestinationSame(escapeVec);
    }

    @Override
    public boolean continueExecuting() {
        return !this.navigator.noPath()
            && this.targetPlayer != null
            && !this.targetPlayer.isDead
            && isValidTarget(this.targetPlayer);
    }

    @Override
    public void startExecuting() {
        this.navigator.setPath(this.escapePath, this.farSpeed);
        suppressCreeperAggression();
        onStartAvoiding();
    }

    @Override
    public void resetTask() {
        this.targetPlayer = null;
        this.escapePath = null;
    }

    @Override
    public void updateTask() {
        if (this.targetPlayer == null) {
            return;
        }

        double distanceSq = this.creeper.getDistanceSqToEntity(this.targetPlayer);
        this.navigator.setSpeed(distanceSq < 49.0D ? this.nearSpeed : this.farSpeed);
        suppressCreeperAggression();
    }

    protected abstract boolean isValidTarget(EntityPlayer player);

    protected void onTargetAcquired(EntityPlayer player) {
    }

    protected void onStartAvoiding() {
    }

    protected void suppressCreeperAggression() {
        clearCreeperTarget(this.targetPlayer);

        if (this.creeper.getCreeperState() > 0) {
            this.creeper.setCreeperState(-1);
        }
    }

    private EntityPlayer getClosestPlayer() {
        return this.creeper.worldObj.getClosestPlayerToEntity(this.creeper, this.avoidDistance, false);
    }

    private void clearCreeperTarget(EntityPlayer player) {
        if (player != null && this.creeper.getAttackTarget() == player) {
            this.creeper.setAttackTarget(null);
        }
    }
}
