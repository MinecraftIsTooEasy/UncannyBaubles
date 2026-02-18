package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.api.ICooldown;
import com.inf1nlty.uncannybaubles.item.ItemEternalCookedSteak;
import com.inf1nlty.uncannybaubles.item.ItemEternalSteak;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@Mixin(EntityPlayer.class)
public abstract class EternalBeefCooldownMixin implements ICooldown {

    @Shadow
    public abstract ItemStack getHeldItemStack();

    @Unique
    private int ub$eternalBeefCooldown = 0;

    @Override
    public int ub$getEternalBeefCooldown() {
        return this.ub$eternalBeefCooldown;
    }

    @Override
    public void ub$setEternalBeefCooldown(int ticks) {
        this.ub$eternalBeefCooldown = Math.max(0, ticks);

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.onServer() && player instanceof ServerPlayer) {
            try {
                this.ub$syncCooldownToClient((ServerPlayer) player);
            } catch (Exception ignored) {}
        }
    }

    @Unique
    private void ub$syncCooldownToClient(ServerPlayer player) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(this.ub$eternalBeefCooldown);

        Packet250CustomPayload packet = new Packet250CustomPayload("UB|SteakCD", bos.toByteArray());
        player.playerNetServerHandler.sendPacketToPlayer(packet);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ub$updateEternalBeefCooldown(CallbackInfo ci) {
        if (this.ub$eternalBeefCooldown > 0) {
            this.ub$eternalBeefCooldown--;
        }
    }

    @Inject(method = "setHeldItemInUse", at = @At("HEAD"), cancellable = true)
    private void ub$blockEternalSteakDuringCooldown(CallbackInfoReturnable<Boolean> cir) {
        ItemStack heldItem = this.getHeldItemStack();

        if (heldItem != null && heldItem.getItem() != null) {

            Item item = heldItem.getItem();

            if ((item instanceof ItemEternalSteak || item instanceof ItemEternalCookedSteak) && this.ub$eternalBeefCooldown > 0) {
                cir.cancel();
            }
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void ub$writeCooldownToNBT(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt != null) {
            nbt.setInteger("ub_eternal_beef_cooldown", this.ub$eternalBeefCooldown);
        }
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void ub$readCooldownFromNBT(NBTTagCompound nbt, CallbackInfo ci) {

        if (nbt != null && nbt.hasKey("ub_eternal_beef_cooldown")) {
            this.ub$eternalBeefCooldown = nbt.getInteger("ub_eternal_beef_cooldown");
        }
        else
        {
            this.ub$eternalBeefCooldown = 0;
        }
    }
}