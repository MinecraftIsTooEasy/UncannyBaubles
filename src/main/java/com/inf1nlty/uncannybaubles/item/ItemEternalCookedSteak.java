package com.inf1nlty.uncannybaubles.item;

import baubles.creativetab.BaublesCreativeTab;
import com.inf1nlty.uncannybaubles.UBConfig;
import com.inf1nlty.uncannybaubles.api.ICooldown;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemEternalCookedSteak extends ItemMeat {


    public ItemEternalCookedSteak(int id) {
        super(id, 10, 10, false, true, "eternal_cooked_steak");
        this.setMaxStackSize(1);
        this.setCreativeTab(BaublesCreativeTab.TAB);
    }

    @Override
    public float getCompostingValue() {
        return 0.0F;
    }

    @Override
    public void onEaten(ItemStack item_stack, World world, EntityPlayer player) {
        super.onEaten(item_stack, world, player);
        if (player instanceof ICooldown) {
            int cooldownTicks = UBConfig.getEternalSteakCooldownTicks();
            ((ICooldown) player).ub$setEternalBeefCooldown(cooldownTicks);
        }
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.addFoodValue(this);
            world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            this.onEaten(item_stack, world, player);
        }
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (player instanceof ICooldown) {
            int cooldown = ((ICooldown) player).ub$getEternalBeefCooldown();
            if (cooldown > 0) {
                return false;
            }
        }
        return super.onItemRightClick(player, partial_tick, ctrl_is_down);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            info.add(I18n.getString("item.uncannybaubles:eternal_steak.info"));

            if (player instanceof ICooldown) {
                int cooldownTicks = ((ICooldown) player).ub$getEternalBeefCooldown();
                if (cooldownTicks > 0) {
                    int seconds = (cooldownTicks + 19) / 20;
                    info.add(String.format(I18n.getString("item.uncannybaubles:eternal_steak.cooldown"), seconds));
                }
            }
        }
    }
}