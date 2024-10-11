package net.zed964.obscure_stars.vue.fog;

import lombok.Getter;
import lombok.Setter;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStateAnimationColorFog;

public abstract class CustomFogColor {

    @Getter
    @Setter
    protected CustomFogCapImpl.StateAnimationCustomFog stateAnimationFogColor = CustomFogCapImpl.StateAnimationCustomFog.INACTIVE;

    protected float beginningRed;

    protected float beginningBlue;

    protected float beginningGreen;

    protected float currentRed;

    protected float currentBlue;

    protected float currentGreen;

    protected CustomFogColor() {

    }

    /**
     * Stock les valeurs de la couleur au début de l'animation du brouillard
     * @param event Event lors du rendu de la couleur du brouillard
     */
    public void setBeginningValueWhenStartingColor(ViewportEvent.ComputeFogColor event) {
        beginningRed = event.getRed();
        beginningGreen = event.getGreen();
        beginningBlue = event.getBlue();

        currentRed = beginningRed;
        currentGreen = beginningGreen;
        currentBlue = beginningBlue;

        stateAnimationFogColor = CustomFogCapImpl.StateAnimationCustomFog.APPEARING;
        ObscureStarsPackets.sendToServer(new C2SSyncStateAnimationColorFog(stateAnimationFogColor.toString()));
    }

    /**
     * Renvoie la vitesse de l'animation de la couleur
     * @param target Valeur de fin d'animation
     * @param currentValue Valeur actuelle de l'animation
     * @return La vitesse d'augmentation de l'animation (négative si on diminue et positif si on augmente)
     */
    public float speedAnimationColor(float target, float currentValue) {
        if (currentValue == target) {
            return 0F;
        }
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
    public boolean isFinishAnimatedColor(float redTarget, float blueTarget, float greenTarget) {
        return currentRed == redTarget && currentBlue == blueTarget && currentGreen == greenTarget;
    }

    /**
     * Vérifie si une animation est en cours
     * @return True si l'animation est en cours
     */
    public boolean isAnimating() {
        return this.stateAnimationFogColor != CustomFogCapImpl.StateAnimationCustomFog.INACTIVE;
    }

    /**
     * Set toutes les valeurs à 0 quand l'animation est terminé
     */
    public void setValueForAnimationColorInactive() {
        currentRed = 0.0F;
        currentBlue = 0.0F;
        currentGreen = 0.0F;
        beginningRed = 0.0F;
        beginningBlue = 0.0F;
        beginningGreen = 0.0F;
        stateAnimationFogColor = CustomFogCapImpl.StateAnimationCustomFog.INACTIVE;
        ObscureStarsPackets.sendToServer(new C2SSyncStateAnimationColorFog(stateAnimationFogColor.toString()));
    }
}
