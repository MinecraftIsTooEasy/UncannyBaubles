package com.inf1nlty.uncannybaubles.emi;

import com.inf1nlty.uncannybaubles.item.UBItems;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.Translator;
import shims.java.net.minecraft.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UBEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        addLuckyCloverInfo(registry);
        addBezoarInfo(registry);
        addRegenRingInfo(registry);
        addMagnetInfo(registry);
        addWaterWalkerInfo(registry);
        addLavaWalkerInfo(registry);
        addLavaCharmInfo(registry);
        addBottledCloudInfo(registry);
        addPocketPistonInfo(registry);
        addKittySlippersInfo(registry);
        addFierceKittySlippersInfo(registry);
        addEternalSteakInfo(registry);
        addLuckyHorseshoeInfo(registry);
    }

    private void addLuckyCloverInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.lucky_clover))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.lucky_clover.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.lucky_clover.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.lucky_clover.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/lucky_clover")
        ));
    }

    private void addBezoarInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.bezoar))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.bezoar.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.bezoar.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.bezoar.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/bezoar")
        ));
    }

    private void addRegenRingInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.regen_bracelet))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.regen_bracelet.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.regen_bracelet.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.regen_bracelet.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/regen_ring")
        ));
    }

    private void addMagnetInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.magnet))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.magnet.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.magnet.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.magnet.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/magnet")
        ));
    }

    private void addWaterWalkerInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.water_walking_boots))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.water_walking_boots.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.water_walking_boots.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.water_walking_boots.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/water_walking_boots")
        ));
    }

    private void addLavaWalkerInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.lava_walking_boots))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.lava_walking_boots.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.lava_walking_boots.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.lava_walking_boots.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/lava_walking_boots")
        ));
    }

    private void addLavaCharmInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.lava_charm))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.lava_charm.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.lava_charm.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.lava_charm.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/lava_charm")
        ));
    }

    private void addBottledCloudInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.bottled_cloud))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.bottled_cloud.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.bottled_cloud.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.bottled_cloud.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/bottled_cloud")
        ));
    }

    private void addPocketPistonInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.pocket_piston))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.pocket_piston.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.pocket_piston.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.pocket_piston.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/pocket_piston")
        ));
    }

    private void addKittySlippersInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.kitty_slippers))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.kitty_slippers.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.kitty_slippers.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.kitty_slippers.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/kitty_slippers")
        ));
    }

    private void addFierceKittySlippersInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.fierce_kitty_slippers))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.fierce_kitty_slippers.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.fierce_kitty_slippers.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.fierce_kitty_slippers.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/fierce_kitty_slippers")
        ));
    }

    private void addEternalSteakInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Arrays.asList(
            EmiStack.of(new ItemStack(UBItems.eternal_steak)),
            EmiStack.of(new ItemStack(UBItems.eternal_cooked_steak))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.eternal_steak.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.eternal_steak.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.eternal_steak.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/eternal_steak")
        ));
    }

    private void addLuckyHorseshoeInfo(EmiRegistry registry) {
        List<EmiIngredient> stacks = Collections.singletonList(
            EmiStack.of(new ItemStack(UBItems.lucky_horseshoe))
        );

        List<Text> text = Arrays.asList(
            Text.literal("§6" + Translator.get("item.lucky_horseshoe.name")),
            Text.literal(""),
            Text.literal("§b" + Translator.get("emi.lucky_horseshoe.desc")),
            Text.literal(""),
            Text.literal("§e" + Translator.get("emi.lucky_horseshoe.obtain"))
        );

        registry.addRecipe(new EmiInfoRecipe(
            stacks,
            text,
            new ResourceLocation("uncannybaubles", "info/lucky_horseshoe")
        ));
    }
}
