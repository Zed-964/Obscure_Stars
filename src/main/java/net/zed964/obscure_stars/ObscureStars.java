package net.zed964.obscure_stars;

import lombok.extern.slf4j.Slf4j;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.items.ObscureStarsItems;
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
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        log.info("HELLO FROM COMMON SETUP");
        log.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
}
