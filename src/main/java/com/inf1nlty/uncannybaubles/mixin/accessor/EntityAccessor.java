package com.inf1nlty.uncannybaubles.mixin.accessor;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("hurtResistantTime")
    int getHurtResistantTime();

    @Accessor("hurtResistantTime")
    void setHurtResistantTime(int value);
}