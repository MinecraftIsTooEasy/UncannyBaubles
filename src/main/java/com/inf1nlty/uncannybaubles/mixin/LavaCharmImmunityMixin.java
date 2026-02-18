package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.ILavaImmunity;
import com.inf1nlty.uncannybaubles.item.UBItems;
import com.inf1nlty.uncannybaubles.util.BaublesUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class LavaCharmImmunityMixin implements ILavaImmunity {

    @Unique
    private int ub$lavaImmunityTicks = 0;

    @Unique
    private static final int MAX_LAVA_IMMUNITY_TICKS = 140; // 7 seconds

    @Override
    public int ub$getLavaImmunityTicks() {
        return this.ub$lavaImmunityTicks;
    }

    @Override
    public void ub$setLavaImmunityTicks(int ticks) {
        this.ub$lavaImmunityTicks = Math.max(0, Math.min(ticks, MAX_LAVA_IMMUNITY_TICKS));
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateLavaImmunity(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;

        boolean hasLavaCharm = UBItems.lava_charm != null && BaublesUtil.hasBaubleWorn(player, UBItems.lava_charm);
        boolean hasLavaBoots = UBItems.lava_walking_boots != null && BaublesUtil.hasBaubleWorn(player, UBItems.lava_walking_boots);

        if (hasLavaCharm || hasLavaBoots)
        {
            boolean inLava = player.handleLavaMovement();
            boolean isBurning = player.isBurning();
            boolean inFire = ub$isInFireBlock(player);
            boolean shouldConsume = inLava || isBurning || inFire;

            if (shouldConsume)
            {
                if (this.ub$lavaImmunityTicks > 0)
                {
                    this.ub$lavaImmunityTicks--;
                }
            }
            else
            {
                if (this.ub$lavaImmunityTicks < MAX_LAVA_IMMUNITY_TICKS)
                {
                    this.ub$lavaImmunityTicks++;
                }
            }
        }
        else
        {
            this.ub$lavaImmunityTicks = 0;
        }
    }

    @Unique
    private boolean ub$isInFireBlock(EntityPlayer player) {
        if (player == null || player.worldObj == null) return false;

        World world = player.worldObj;
        int minX = MathHelper.floor_double(player.boundingBox.minX);
        int minY = MathHelper.floor_double(player.boundingBox.minY);
        int minZ = MathHelper.floor_double(player.boundingBox.minZ);
        int maxX = MathHelper.floor_double(player.boundingBox.maxX);
        int maxY = MathHelper.floor_double(player.boundingBox.maxY);
        int maxZ = MathHelper.floor_double(player.boundingBox.maxZ);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlock(x, y, z);
                    if (block != null && block.blockMaterial == Material.fire) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void ub$preventLavaDamage(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        EntityPlayer player = (EntityPlayer) (Object) this;

        if (this.ub$lavaImmunityTicks > 0) {
            if (damage != null && (damage.isFireDamage() || damage.isLavaDamage())) {
                cir.setReturnValue(null);
                player.extinguish();
            }
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeImmunityToNBT(NBTTagCompound nbt, CallbackInfo ci) {

        if (nbt != null) {
            nbt.setInteger("ub_lava_immunity_ticks", this.ub$lavaImmunityTicks);
        }
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readImmunityFromNBT(NBTTagCompound nbt, CallbackInfo ci) {

        if (nbt != null && nbt.hasKey("ub_lava_immunity_ticks")) {
            this.ub$lavaImmunityTicks = nbt.getInteger("ub_lava_immunity_ticks");
        }
        else
        {
            this.ub$lavaImmunityTicks = 0;
        }
    }
}