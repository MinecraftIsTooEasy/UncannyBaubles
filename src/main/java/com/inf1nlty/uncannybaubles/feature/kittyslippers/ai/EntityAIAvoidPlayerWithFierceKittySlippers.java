package com.inf1nlty.uncannybaubles.feature.kittyslippers.ai;

import com.inf1nlty.uncannybaubles.api.IFierceKittySlippersCooldown;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
import net.minecraft.*;

public class EntityAIAvoidPlayerWithFierceKittySlippers extends EntityAIAvoidPlayerWithBauble {

    private boolean hasTriggeredSideEffect = false;

    public EntityAIAvoidPlayerWithFierceKittySlippers(EntityCreeper creeper, float avoidDistance, double farSpeed, double nearSpeed) {
        super(creeper, avoidDistance, farSpeed, nearSpeed);
    }

    @Override
    protected boolean isValidTarget(EntityPlayer player) {
        if (!KittySlippersEffect.hasFierceKittySlippers(player)) {
            return false;
        }

        return IFierceKittySlippersCooldown.get(player) <= 0;
    }

    @Override
    protected void onTargetAcquired(EntityPlayer player) {
        this.hasTriggeredSideEffect = false;
    }

    @Override
    protected void onStartAvoiding() {
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
        IFierceKittySlippersCooldown.set(this.targetPlayer, 6000);

        this.targetPlayer.worldObj.playSoundAtEntity(
            this.targetPlayer,
            UBSounds.fierce_kitty_slippers.toString(),
            1.0F,
            1.0F
        );
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.hasTriggeredSideEffect = false;
    }
}
