package com.inf1nlty.uncannybaubles.feature.kittyslippers.ai;

import com.inf1nlty.uncannybaubles.feature.kittyslippers.KittySlippersEffect;
import net.minecraft.*;

public class EntityAIAvoidPlayerWithKittySlippers extends EntityAIAvoidPlayerWithBauble {

    public EntityAIAvoidPlayerWithKittySlippers(EntityCreeper creeper, float avoidDistance, double farSpeed, double nearSpeed) {
        super(creeper, avoidDistance, farSpeed, nearSpeed);
    }

    @Override
    protected boolean isValidTarget(EntityPlayer player) {
        return KittySlippersEffect.hasKittySlippers(player);
    }
}
