package com.inf1nlty.uncannybaubles.mixin;

import com.inf1nlty.uncannybaubles.item.UBItems;
import net.minecraft.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public class TileEntityFurnaceMixin {

    @Inject(method = "getHeatLevelRequired(I)I", at = @At("HEAD"), cancellable = true)
    private static void onGetHeatLevelRequired(int itemID, CallbackInfoReturnable<Integer> cir) {

        if (itemID == UBItems.eternal_steak.itemID) {
            cir.setReturnValue(4);
        }
    }
}