package net.zed964.obscure_stars.utils;

import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

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
     * Function qui renvoie vraie si l'entité rejoin la dimension en paramètre
     * @param event Event lorsqu'une entité rejoint un monde
     * @param dimensionName Nom de la dimension
     * @return True si l'entité rejoint la dimension en paramètre
     */
    public static boolean entityJoinLevelGoToDimension(EntityJoinLevelEvent event, String dimensionName) {
        return event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    /**
     * Function qui renvoie vraie si l'entité n'est pas dans la dimension en paramètre
     * @param event Event lorsqu'une entité rejoint un monde
     * @param dimensionName Nom de la dimension
     * @return True si l'entité n'est pas dans la dimension en paramètre
     */
    public static boolean entityJoinLevelIsNotInDimension(EntityJoinLevelEvent event, String dimensionName) {
        return !event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    /**
     * Function qui renvoie vraie si l'entité modifie son équipement dans la dimension en paramètre
     * @param event Event lorsqu'une entité modifie son équipement
     * @param dimensionName Nom de la dimension
     * @return True si l'entité modifie son équipement dans la dimension en paramètre
     */
    public static boolean entityWhenEquipmentChangeInDimension(LivingEquipmentChangeEvent event, String dimensionName) {
        return event.getEntity().getLevel().dimension().location().getPath().equals(dimensionName);
    }
}
