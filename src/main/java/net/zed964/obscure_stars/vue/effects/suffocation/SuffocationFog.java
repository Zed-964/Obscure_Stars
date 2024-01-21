package net.zed964.obscure_stars.vue.effects.suffocation;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.utils.MethodUtils;

/**
 * Class qui gère l'animation du brouillard pour l'effet suffocation
 */
public class SuffocationFog {

    private boolean isProcessingFog;

    private boolean isAppliedFog;

    private boolean isAnimatingNearFog;

    private boolean isAnimatingFarFog;

    private float currentNearPlaneDistance;

    private float currentFarPlaneDistance;

    /**
     * Constructeur par défaut
     */
    public SuffocationFog() {
        isProcessingFog = false;
        isAppliedFog = false;
        isAnimatingNearFog = false;
        isAnimatingFarFog = false;
    }

    /**
     * Stock les valeurs de la distance actuelle du brouillard
     * @param event Event lors du rendu du brouillard
     */
    public void setCurrentValueWhenStartingFog(ViewportEvent.RenderFog event) {
        currentNearPlaneDistance = event.getRenderer().getRenderDistance() / 1.15F;
        currentFarPlaneDistance = event.getRenderer().getRenderDistance() * 1.225F;
        isProcessingFog = true;
    }

    /**
     * Applique la distance proche du brouillard lors de l'animation
     * @param event Event lors du rendu du brouillard
     */
    public void animatingNearFog(ViewportEvent.RenderFog event) {
        if (currentNearPlaneDistance > EffectsConstants.SUFFOCATION_FOG_START) {
            isAnimatingNearFog = true;

            //On diminue la valeur currentNearPlaneDistance selon la distance à laquelle la cible est fixé (ici 0, car on est sur l'effet de suffocation)
            currentNearPlaneDistance -= animationSlowByDistance(EffectsConstants.SUFFOCATION_FOG_START, currentNearPlaneDistance);

            //On vérifie que l'on ne dépasse pas la valeur cible (ici 0, car on est sur l'effet de suffocation)
            currentNearPlaneDistance = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_START, currentNearPlaneDistance);
        }

        if(EffectsConstants.SUFFOCATION_FOG_START == currentNearPlaneDistance) {
            isAnimatingNearFog = false;
        }

        event.setNearPlaneDistance(currentNearPlaneDistance);
    }

    /**
     * Applique la distance lointaine du brouillard lors de l'animation
     * @param event Event lors du rendu du brouillard
     */
    public void animatingFarFog(ViewportEvent.RenderFog event) {
        if (currentFarPlaneDistance > EffectsConstants.SUFFOCATION_FOG_END) {
            isAnimatingFarFog = true;

            //On diminue la valeur currentNearPlaneDistance selon la distance à laquelle la cible est fixé (ici 0, car on est sur l'effet de suffocation)
            currentFarPlaneDistance -= animationSlowByDistance(EffectsConstants.SUFFOCATION_FOG_END, currentFarPlaneDistance);

            //On vérifie que l'on ne dépasse pas la valeur cible (ici 0, car on est sur l'effet de suffocation)
            currentFarPlaneDistance = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_END, currentFarPlaneDistance);
        }

        if(EffectsConstants.SUFFOCATION_FOG_END == currentFarPlaneDistance) {
            isAnimatingFarFog = false;
        }

        event.setFarPlaneDistance(currentFarPlaneDistance);
    }

    /**
     * Applique la distance finale du brouillard lorsque l'animation est terminé
     * @param event Event lors du rendu du brouillard
     */
    public void fogFinalApplied(ViewportEvent.RenderFog event) {
        event.setNearPlaneDistance(EffectsConstants.SUFFOCATION_FOG_START);
        event.setFarPlaneDistance(EffectsConstants.SUFFOCATION_FOG_END);
    }

    /**
     * Change les valeurs d'état lorsque l'animation est terminé
     */
    public void animatingFinish() {
        if (!isAnimatingNearFog && !isAnimatingFarFog) {
            isProcessingFog = false;
            isAppliedFog = true;
        }
    }

    /**
     * Change la vitesse de l'animation selon la distance du brouillard
     * @param target La distance que l'in vise
     * @param current La distance actuelle où l'on se trouve
     * @return La valeur de diminution de la distance
     */
    private float animationSlowByDistance(float target, float current) {
        if (current > target + 300) {
            return 0.15F;
        } else {
            if (current > target + 100) {
                return 0.075F;
            } else {
                if (current > target + 35) {
                    return 0.035F;
                } else {
                    if (current > target + 5) {
                        return 0.015F;
                    } else {
                        return 0.005F;
                    }
                }
            }
        }
    }

    /**
     * Change toutes les valeurs d'état sur False
     */
    public void setAllToFalse() {
        isProcessingFog = false;
        isAppliedFog = false;
        isAnimatingNearFog = false;
        isAnimatingFarFog = false;
    }

    /**
     * Getter sur l'état de début de l'animation
     * @return True si l'animation est en cours d'exécution sinon False
     */
    public boolean getIsProcessingFog() {
        return isProcessingFog;
    }

    /**
     * Getter sur l'état de fin de l'animation
     * @return True si l'animation est terminé
     */
    public boolean getIsAppliedFog() {
        return isAppliedFog;
    }
}
