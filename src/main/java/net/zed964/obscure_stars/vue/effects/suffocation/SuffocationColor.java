package net.zed964.obscure_stars.vue.effects.suffocation;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.utils.MethodUtils;

/**
 * Class qui gère l'animation de la couleur du brouillard pour l'effet suffocation
 */
public class SuffocationColor {

    private boolean isIsProcessingColor;

    private boolean isAppliedColor;

    private float currentRed;

    private float currentGreen;

    private float currentBlue;

    /**
     * Constructeur par défaut
     */
    public SuffocationColor() {
        isIsProcessingColor = false;
        isAppliedColor = false;
    }

    /**
     * Stock les valeurs de la couleur actuelle du brouillard
     * @param event Event lors du rendu de la couleur du brouillard
     */
    public void setCurrentValueWhenStartingColor(ViewportEvent.ComputeFogColor event) {
        currentRed = event.getRed();
        currentGreen = event.getGreen();
        currentBlue = event.getBlue();
        isIsProcessingColor = true;
    }

    /**
     * Applique la couleur finale du brouillard lorsque l'animation est terminé
     * @param event Event lors du rendu de la couleur du brouillard
     */
    public void colorFinalApplied(ViewportEvent.ComputeFogColor event) {
        event.setRed(EffectsConstants.SUFFOCATION_FOG_COLOR_RED);
        event.setGreen(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN);
        event.setBlue(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE);
    }

    /**
     * Change les valeurs d'état lorsque l'animation est terminé
     */
    public void colorFinish() {
        if (currentRed == EffectsConstants.SUFFOCATION_FOG_COLOR_RED
                && currentGreen == EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN
                && currentBlue == EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE) {
            isIsProcessingColor = false;
            isAppliedColor = true;
        }
    }

    /**
     * Applique la couleur lors de l'animation du brouillard
     * @param event Event lors du rendu de la couleur du brouillard
     */
    public void setColorOnFog(ViewportEvent.ComputeFogColor event) {
        currentRed -= 0.0004F;
        currentGreen -= 0.0004F;
        currentBlue -= 0.0004F;

        currentRed = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_RED, currentRed);
        currentGreen = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN, currentGreen);
        currentBlue = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE, currentBlue);

        event.setRed(currentRed);
        event.setGreen(currentGreen);
        event.setBlue(currentBlue);
    }

    /**
     * Change toutes les valeurs d'état sur False
     */
    public void setAllToFalse() {
        isIsProcessingColor = false;
        isAppliedColor = false;
    }

    /**
     * Getter sur l'état de début de l'animation
     * @return True si l'animation est en cours d'exécution sinon False
     */
    public boolean getIsProcessingColor() {
        return isIsProcessingColor;
    }

    /**
     * Getter sur l'état de fin de l'animation
     * @return True si l'animation est terminé
     */
    public boolean getIsAppliedColor() {
        return isAppliedColor;
    }
}
