package net.zed964.obscure_stars.controler.config;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.constants.ObscureStarsConstants;
import net.zed964.obscure_stars.model.items.ObscureStarsItems;

@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ObscureStarsCreativeTab {

    public static CreativeModeTab OBSCURE_STARS_TAB;

    private ObscureStarsCreativeTab() {

    }

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        OBSCURE_STARS_TAB = event.registerCreativeModeTab(new ResourceLocation(ObscureStars.MOD_ID, ObscureStarsConstants.TAB_PATH),
                builder -> builder
                        .icon(() -> new ItemStack(ObscureStarsItems.SPACESUIT_HELMET.get()))
                        .title(Component.translatable(ObscureStarsConstants.TRANSLATABLE_TAB_KEY))
                        .build());
    }
}
