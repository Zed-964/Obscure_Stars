package net.zed964.obscure_stars.utils;

/**
 * Class utilitaire avec des fonctions sans rapports avec le jeu
 */
public class MethodUtils {

    /**
     * Constructeur par défaut de la classe
     */
    private MethodUtils() {

    }

    /**
     * Fonction renvoie la plus grande valeur des 2 paramètres
     * @param target Valeur que l'on essaye d'atteindre
     * @param value Valeur actuelle que l'on a
     * @return La plus grande valeur
     */
    public static float keepMaxValue(float target, float value) {
        return Math.max(target, value);
    }
}
