package net.zed964.obscure_stars.utils;

import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

import net.zed964.obscure_stars.config.ObscureStarsConfig;

/**
 * Class utilitaire avec des fonctions en rapport avec les events
 */
public class EventUtils {

    /**
     * Constructeur par défaut de la classe
     */
    private EventUtils() {

    }

    /**
     * Function qui renvoie vraie si on est coté serveur
     * @param event Event d'entité
     * @return True coté serveur, false coté client
     */
    public static boolean entityJoinLevelIsServerSide(EntityEvent event) {
        return !event.getEntity().getLevel().isClientSide();
    }

    /**
     * Function qui renvoie vraie si l'entité rejoin une dimension ayant l'effet suffocation
     * @param event Event lorsqu'une entité rejoint un monde
     * @return True si l'entité rejoint une dimension avec l'effet de suffocation de paramétrer
     */
    public static boolean entityJoinLevelHasSuffocation(EntityJoinLevelEvent event) {
        for (String dimensionPath : ObscureStarsConfig.getInstance().getDimensionsHasSuffocation()) {
            if (event.getLevel().dimension().location().toString().equals(dimensionPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function qui renvoie vraie si l'entité modifie son équipement dans une dimension ou l'effet suffocation est paramétré
     * @param event Event lorsqu'une entité modifie son équipement
     * @return True si l'entité modifie son équipement dans une dimension ayant l'effet de suffocation
     */
    public static boolean entityChangeEquipmentInDimensionHasSuffocation(LivingEquipmentChangeEvent event) {
        for (String dimensionPath : ObscureStarsConfig.getInstance().getDimensionsHasSuffocation()) {
            if (event.getEntity().getLevel().dimension().location().toString().equals(dimensionPath)) {
                return true;
            }
        }
        return false;
    }
}
