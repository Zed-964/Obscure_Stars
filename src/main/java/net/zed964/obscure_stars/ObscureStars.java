package net.zed964.obscure_stars;

import lombok.extern.slf4j.Slf4j;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.zed964.obscure_stars.config.file.ObscureStarsFileConfig;
import net.zed964.obscure_stars.constants.ObscureStarsConstants;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.items.ObscureStarsItems;
import net.zed964.obscure_stars.model.menus.ObscureStarsMenus;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;

import net.zed964.obscure_stars.vue.gui.screens.customs.TestScreen;
import software.bernie.geckolib.GeckoLib;

/**
 * Class principal du mod pour le lancement
 */
@Slf4j
@Mod(ObscureStars.MOD_ID)
public class ObscureStars {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "obscure_stars";

    /**
     * Main method of the project
     */
    public ObscureStars() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register all menus
        ObscureStarsMenus.register(modEventBus);

        // Register all items
        ObscureStarsItems.register(modEventBus);

        // Register the effects
        ObscureStarsEffects.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ObscureStarsItems::addCreativeTab);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ObscureStarsFileConfig.SPEC, ObscureStarsConstants.FILE_NAME_CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ObscureStarsPackets.register();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientObscureStarsEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ObscureStarsMenus.TEST_MENU.get(), TestScreen::new); //(menu, inventory, title) -> new TestScreen(menu, inventory, TestScreen.screenTitle));
        }
    }
}

