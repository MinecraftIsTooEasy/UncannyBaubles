package com.inf1nlty.uncannybaubles.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import baubles.creativetab.BaublesCreativeTab;
import net.minecraft.*;

import java.util.List;

public abstract class ItemBaseBaubles extends Item implements IBauble {

    public ItemBaseBaubles(int id, Material material, String texture) {
        super(id, material, texture);
        this.setMaxStackSize(1);
        this.setCreativeTab(BaublesCreativeTab.TAB);
    }

    public ItemBaseBaubles(int id, Material material) {
        this(id, material, null);
    }

    @Override
    public abstract BaubleType getBaubleType(ItemStack itemstack);

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
        ItemStack item_stack = player.getHeldItemStack();
        for (int i = 0; i < baubles.getSizeInventory(); i++) {
            if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, item_stack)) {
                if (player.onServer()) {
                    baubles.setInventorySlotContents(i, item_stack.copy());
                    if (!player.capabilities.isCreativeMode) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    }
                    onEquipped(item_stack, player);
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        if (!player.worldObj.isRemote) {
            player.worldObj.playSoundAtEntity(player, "random.orb", 0.1F, 1.3f);
        }
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformationBeforeEnchantments(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(I18n.getString("item." + this.getIconString() + ".info"));
        }
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
