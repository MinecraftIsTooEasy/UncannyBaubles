package com.inf1nlty.uncannybaubles.emi;

import com.inf1nlty.uncannybaubles.UncannyBaublesMod;
import com.inf1nlty.uncannybaubles.item.UBItems;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.Translator;
import shims.java.net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UBEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        addInfo(registry, "lucky_clover", UBItems.lucky_clover);
        addInfo(registry, "bezoar", UBItems.bezoar);
        addInfo(registry, "regen_bracelet", "regen_ring", UBItems.regen_bracelet);
        addInfo(registry, "magnet", UBItems.magnet);
        addInfo(registry, "water_walking_boots", UBItems.water_walking_boots);
        addInfo(registry, "lava_walking_boots", UBItems.lava_walking_boots);
        addInfo(registry, "lava_charm", UBItems.lava_charm);
        addInfo(registry, "bottled_cloud", UBItems.bottled_cloud);
        addInfo(registry, "shiny_red_balloon", UBItems.shiny_red_balloon);
        addInfo(registry, "cloud_in_a_balloon", UBItems.cloud_in_a_balloon);
        addInfo(registry, "pocket_piston", UBItems.pocket_piston);
        addInfo(registry, "kitty_slippers", UBItems.kitty_slippers);
        addInfo(registry, "fierce_kitty_slippers", UBItems.fierce_kitty_slippers);
        addInfo(registry, "eternal_steak", UBItems.eternal_steak, UBItems.eternal_cooked_steak);
        addInfo(registry, "lucky_horseshoe", UBItems.lucky_horseshoe);
        addInfo(registry, "blue_horseshoe_balloon", UBItems.blue_horseshoe_balloon);
        addInfo(registry, "cross_necklace", UBItems.cross_necklace);
        addInfo(registry, "hermes_boots", UBItems.hermes_boots);
        addInfo(registry, "broken_ankh", UBItems.broken_ankh);
        addInfo(registry, "digging_claws", UBItems.digging_claws);
        addInfo(registry, "onion_ring", UBItems.onion_ring);
    }

    private void addInfo(EmiRegistry registry, String key, Item... items) {
        addInfo(registry, key, key, items);
    }

    private void addInfo(EmiRegistry registry, String translationKey, String recipeKey, Item... items) {
        List<EmiIngredient> stacks = new ArrayList<>(items.length);
        for (Item item : items) {
            stacks.add(EmiStack.of(new ItemStack(item)));
        }

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item." + translationKey + ".name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi." + translationKey + ".desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi." + translationKey + ".obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation(UncannyBaublesMod.NAMESPACE, "info/" + recipeKey)
        ));
    }
}
