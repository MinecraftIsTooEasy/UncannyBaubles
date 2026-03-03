package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@Mixin(EntityPlayer.class)
public abstract class BrokenAnkhCooldownMixin implements IBrokenAnkhCooldown {


    @Unique private int ub$brokenAnkhCooldown = 0;

    @Override
    public int ub$getBrokenAnkhCooldown() {
        return this.ub$brokenAnkhCooldown;
    }

    @Override
    public void ub$setBrokenAnkhCooldown(int ticks) {
        this.ub$brokenAnkhCooldown = Math.max(0, ticks);

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.onServer() && player instanceof ServerPlayer) {
            try {
                ub$syncToClient((ServerPlayer) player);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void ub$setBrokenAnkhCooldownRaw(int ticks) {
        this.ub$brokenAnkhCooldown = Math.max(0, ticks);
    }

    @Unique
    private void ub$syncToClient(ServerPlayer player) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(this.ub$brokenAnkhCooldown);
        player.playerNetServerHandler.sendPacketToPlayer(
            new Packet250CustomPayload("UB|AnkhCD", bos.toByteArray())
        );
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$tickBrokenAnkhCooldown(CallbackInfo ci) {
        if (this.ub$brokenAnkhCooldown > 0) {
            this.ub$brokenAnkhCooldown--;
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeAnkhCooldownNBT(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt != null) {
            nbt.setInteger("ub_broken_ankh_cooldown", this.ub$brokenAnkhCooldown);
        }
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readAnkhCooldownNBT(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt != null && nbt.hasKey("ub_broken_ankh_cooldown")) {
            this.ub$brokenAnkhCooldown = nbt.getInteger("ub_broken_ankh_cooldown");
        } else {
            this.ub$brokenAnkhCooldown = 0;
        }
    }
}