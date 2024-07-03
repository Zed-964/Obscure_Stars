package net.zed964.obscure_stars;

import lombok.extern.slf4j.Slf4j;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.zed964.obscure_stars.config.file.ObscureStarsFileConfig;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.items.ObscureStarsItems;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;

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

        // Register all items
        ObscureStarsItems.register(modEventBus);

        // Register the effects
        ObscureStarsEffects.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ObscureStarsFileConfig.SPEC, ObscureStarsFileConfig.FILE_NAME_CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ObscureStarsPackets.register();
    }
}
