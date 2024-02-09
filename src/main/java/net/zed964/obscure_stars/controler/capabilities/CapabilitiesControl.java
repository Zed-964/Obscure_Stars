package net.zed964.obscure_stars.controler.capabilities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.constants.CapabilitiesConstants;
import net.zed964.obscure_stars.model.capabilities.provider.CustomFogProvider;
import net.zed964.obscure_stars.model.capabilities.CustomFogCap;

/**
 * Contrôle des capabilities du mod sur un joueur
 */
@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilitiesControl {

    /**
     * Constructeur privé par défaut
     */
    private CapabilitiesControl() {

    }

    /**
     * Registre toutes les capabilities que rajoute le mod
     * @param event Au lancement du jeu pour register toutes la capabilities du mod
     */
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CustomFogCap.class);
    }

    /**
     * Ajout la capability pour le brouillard custom sur un joueur
     * @param event Quand un objet qui peut obtenir un capability est créé
     */
    @SubscribeEvent
    public static void addFogCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer player
                && (!player.getCapability(CustomFogProvider.PLAYER_CUSTOM_FOG).isPresent())) {
            event.addCapability(new ResourceLocation(ObscureStars.MOD_ID, CapabilitiesConstants.PATH_CUSTOM_FOG), new CustomFogProvider());
        }
    }
}
