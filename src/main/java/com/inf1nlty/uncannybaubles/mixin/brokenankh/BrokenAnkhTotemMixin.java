package com.inf1nlty.uncannybaubles.mixin.brokenankh;

import baubles.api.BaubleSlotHelper;

import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import com.inf1nlty.uncannybaubles.client.UBSounds;
import com.inf1nlty.uncannybaubles.item.UBItems;

import net.minecraft.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Mixin(EntityLivingBase.class)
public abstract class BrokenAnkhTotemMixin {

    @Unique private static final int EFFECT_DURATION = 11 * 20;
    @Unique private static final String PARTICLE_CHANNEL = "UB|AnkhFX";

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void ub$brokenAnkhTotem(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {

        if (!((Object) this instanceof EntityPlayer player)) return;

        if (player.worldObj == null || player.worldObj.isRemote) return;

        if (player.getHealth() - damage.getAmount() > 0.0F) return;

        if (UBItems.broken_ankh == null) return;
        if (!BaubleSlotHelper.hasAmuletOfType(player, UBItems.broken_ankh)) return;

        IBrokenAnkhCooldown cooldown = (IBrokenAnkhCooldown) player;
        if (cooldown.ub$getBrokenAnkhCooldown() > 0) return;

        cir.setReturnValue(null);

        player.setHealth(1.0F);
        player.hurtTime = player.maxHurtTime = 10;
        player.hurtResistantTime = 40;
        player.limbSwingAmount = 1.5F;

        player.clearActivePotions();

        player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, EFFECT_DURATION, 3));
        player.addPotionEffect(new PotionEffect(Potion.regeneration.id, EFFECT_DURATION, 2));
        player.addPotionEffect(new PotionEffect(Potion.resistance.id,   EFFECT_DURATION, 2));

        player.worldObj.setEntityState(player, EnumEntityState.hurt_with_red_tint_refreshed);

        player.worldObj.playSoundAtEntity(player, UBSounds.broken_ankh_totem.toString(), 1.0F, 1.0F);

        if (player instanceof ServerPlayer sp) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            try {
                dos.writeDouble(player.posX);
                dos.writeDouble(player.posY);
                dos.writeDouble(player.posZ);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sp.playerNetServerHandler.sendPacketToPlayer(
                new Packet250CustomPayload(PARTICLE_CHANNEL, bos.toByteArray())
            );
        }

        cooldown.ub$setBrokenAnkhCooldown(36000);
    }
}