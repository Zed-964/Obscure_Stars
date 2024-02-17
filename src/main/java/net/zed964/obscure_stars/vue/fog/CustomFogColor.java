package net.zed964.obscure_stars.vue.fog;

import lombok.Getter;
import lombok.Setter;
import net.minecraftforge.client.event.ViewportEvent;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusColorFog;

public abstract class CustomFogColor {

    @Getter
    @Setter
    protected static CustomFogCapImpl.StatusDirectionCustomFog statusFogColor = CustomFogCapImpl.StatusDirectionCustomFog.OFF;

    protected static float beginningRed;

    protected static float beginningBlue;

    protected static float beginningGreen;

    protected static float currentRed;

    protected static float currentBlue;

    protected static float currentGreen;

    protected static boolean isAnimatingColor = false;

    protected CustomFogColor() {

    }

    /**
     * Stock les valeurs de la couleur au début de l'animation du brouillard
     * @param event Event lors du rendu de la couleur du brouillard
     */
    public static void setBeginningValueWhenStartingColor(ViewportEvent.ComputeFogColor event) {
        beginningRed = event.getRed();
        beginningGreen = event.getGreen();
        beginningBlue = event.getBlue();

        currentRed = beginningRed;
        currentGreen = beginningGreen;
        currentBlue = beginningBlue;
    }

    /**
     * Renvoie la vitesse de l'animation de la couleur
     * @param target Valeur de fin d'animation
     * @param currentValue Valeur actuelle de l'animation
     * @return La vitesse d'augmentation de l'animation (négative si on diminue et positif si on augmente)
     */
    public static float speedAnimationColor(float target, float currentValue) {
        if (currentValue > target) {
            return -0.0005F;
        }
        return 0.0005F;
    }

    /**
     * Vérifie si l'animation en cours est terminé
     * @param redTarget Valeur couleur rouge de fin d'animation
     * @param blueTarget Valeur couleur bleue de fin d'animation
     * @param greenTarget Valeur couleur verte de fin d'animation
     * @return True si toutes les valeurs des couleurs de fin d'animation est atteinte, False si une des valeurs des couleurs ne sont pas atteintes
     */
    public static boolean isFinishAnimatedColor(float redTarget, float blueTarget, float greenTarget) {
        if (currentRed == redTarget && currentBlue == blueTarget && currentGreen == greenTarget) {
            isAnimatingColor = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set toutes les valeurs à 0 quand l'animation est terminé
     */
    public static void setValueForAnimationColorOff() {
        currentRed = 0.0F;
        currentBlue = 0.0F;
        currentGreen = 0.0F;
        beginningRed = 0.0F;
        beginningBlue = 0.0F;
        beginningGreen = 0.0F;
        ObscureStarsPackets.sendToServer(new C2SSyncStatusColorFog(statusFogColor.toString()));
    }
}
