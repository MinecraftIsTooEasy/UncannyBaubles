package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import com.inf1nlty.uncannybaubles.api.IBrokenAnkhCooldown;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemBrokenAnkh extends ItemBaseBaubles {

    public ItemBrokenAnkh(int id, Material material) {
        super(id, material);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

            if (player instanceof IBrokenAnkhCooldown) {
                int cooldownTicks = ((IBrokenAnkhCooldown) player).ub$getBrokenAnkhCooldown();
                if (cooldownTicks > 0) {
                    int totalSeconds = (cooldownTicks + 19) / 20;
                    int minutes = totalSeconds / 60;
                    int seconds = totalSeconds % 60;
                    info.add(String.format(I18n.getString("item.uncannybaubles:broken_ankh.cooldown"), minutes, seconds));
                }
            }
        }
    }
}