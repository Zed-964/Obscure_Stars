package net.zed964.obscure_stars.controler.config;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.config.ObscureStarsConfig;
import net.zed964.obscure_stars.exceptions.ExceptionsEnum;
import net.zed964.obscure_stars.exceptions.custom.BadConfigurationException;
import net.zed964.obscure_stars.exceptions.custom.DimensionNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class qui contrôle les valeurs rentrées dans le fichier de configuration du mod
 */
@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ConfigController {

    /**
     * Constructeur privé par défaut
     */
    private ConfigController() {

    }

    /**
     * Vérifie si les dimensions configurées dans le fichier existe en jeu, si ce n'est pas le cas, on lève une exception
     * @param event Lors du lancement du server
     * @throws BadConfigurationException Exception générique lié au fichier de conf du mod
     */
    @SubscribeEvent
    public static void verifyAllDimensionsHasSuffocation(ServerStartingEvent event) throws BadConfigurationException {
        List<String> dimensionsPathConfig = ObscureStarsConfig.getInstance().getDimensionsHasSuffocation();
        List<String> dimensionsPath = new ArrayList<>();

        event.getServer().getAllLevels().forEach(serverLevel ->
                dimensionsPath.add(serverLevel.dimension().location().toString()));

        for (String path : dimensionsPathConfig) {
            if (dimensionsPath.stream().noneMatch(dimPath -> dimPath.equals(path))) {
                throw new BadConfigurationException(ExceptionsEnum.BAD_CONFIGURATION_DEFAULT, new DimensionNotFoundException(ExceptionsEnum.DIMENSION_CONFIG_HAS_SUFFOCATION_NOT_FOUND));
            }
        }
    }
}
