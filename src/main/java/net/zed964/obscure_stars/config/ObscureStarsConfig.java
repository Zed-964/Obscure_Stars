package net.zed964.obscure_stars.config;


import lombok.Getter;

import net.zed964.obscure_stars.config.file.ObscureStarsFileConfig;

import java.util.List;

/**
 * Class pour gérer la config du mod
 */
@Getter
public final class ObscureStarsConfig {

    private static ObscureStarsConfig instance;

    private final List<String> dimensionsHasSuffocation;

    /**
     * Constructeur par défaut
     */
    private ObscureStarsConfig() {
        this.dimensionsHasSuffocation = (List<String>) ObscureStarsFileConfig.DIMENSIONS_HAS_SUFFOCATION_EFFECT.get();
    }

    /**
     * Singleton Multi thread safe de la classe de config
     * @return Instance de la classe
     */
    public static ObscureStarsConfig getInstance() {
        ObscureStarsConfig result = instance;
        if (result != null) {
            return result;
        }

        synchronized (ObscureStarsConfig.class) {
            if (instance == null) {
                instance = new ObscureStarsConfig();
            }
            return instance;
        }
    }
}
