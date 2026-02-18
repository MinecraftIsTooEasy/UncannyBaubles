package com.inf1nlty.uncannybaubles.ai;

import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;

public class EntityAIAvoidPlayerWithKittySlippers extends EntityAIBase {

    private final EntityCreeper creeper;
    private final float avoidDistance;
    private final double farSpeed;
    private final double nearSpeed;
    private EntityPlayer targetPlayer;
    private PathEntity escapePath;
    private final PathNavigate navigator;

    public EntityAIAvoidPlayerWithKittySlippers(EntityCreeper creeper, float avoidDistance, double farSpeed, double nearSpeed) {
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

        if (!BaublesUtil.hasBaubleWorn(nearestPlayer, UBItems.kitty_slippers)) {
            return false;
        }

        if (this.creeper.getAttackTarget() == nearestPlayer) {
            this.creeper.setAttackTarget(null);
        }

        this.targetPlayer = nearestPlayer;

        Vec3 playerVec = this.creeper.worldObj.getWorldVec3Pool().getVecFromPool(
            nearestPlayer.posX,
            nearestPlayer.posY,
            nearestPlayer.posZ
        );

        Vec3 escapeVec = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
            this.creeper,
            16,  // Search range
            7,   // Vertical range
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

        return BaublesUtil.hasBaubleWorn(this.targetPlayer, UBItems.kitty_slippers);
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
    }

    @Override
    public void resetTask() {
        this.targetPlayer = null;
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
