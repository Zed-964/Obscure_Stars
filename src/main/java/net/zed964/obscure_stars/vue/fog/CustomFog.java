package net.zed964.obscure_stars.vue.fog;

import lombok.Getter;
import lombok.Setter;

import net.minecraft.client.renderer.GameRenderer;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusFog;

import org.jetbrains.annotations.NotNull;

public abstract class CustomFog {

    @Getter
    @Setter
    protected CustomFogCapImpl.StatusDirectionCustomFog statusFog = CustomFogCapImpl.StatusDirectionCustomFog.OFF;

    protected float beginningNearFogPos;

    protected float beginningFarFogPos;

    protected float currentNearFogPos;

    protected float currentFarFogPos;

    protected boolean isActiveAnimation = false;

    protected boolean isAnimatingNearFog = true;

    protected boolean isAnimatingFarFog = true;

    protected CustomFog() {

    }

    /**
     * Set toutes les valeurs du brouillard pour le début de l'animation
     * @param renderer Rendu du client
     */
    public void setBeginningValueWhenStartingFog(@NotNull GameRenderer renderer) {
        beginningNearFogPos = renderer.getRenderDistance() / 1.25F;
        beginningFarFogPos = renderer.getRenderDistance();

        currentNearFogPos = beginningNearFogPos;
        currentFarFogPos = beginningFarFogPos;
    }

    /**
     * Renvoie la valeur de la vitesse de l'animation en fonction de la distance
     * @param targetNear Valeur proche de fin d'animation
     * @param targetFar Valeur éloignée de fin d'animation
     * @return la vitesse d'augmentation de l'animation (négative si on diminue et positif si on augmente)
     */
    protected float speedAnimationFog(float targetNear, float targetFar) {
        if (statusFog == CustomFogCapImpl.StatusDirectionCustomFog.DECREASE) {
            if (currentNearFogPos > targetNear + 200) {
                return -0.16F;
            }
            if (currentFarFogPos > targetNear + 100) {
                return -0.08F;
            }
            if (currentFarFogPos > targetNear + 30) {
                return -0.04F;
            }
            if (currentFarFogPos > targetNear + 5) {
                return -0.02F;
            }
            return -0.01F;
        }

        if (currentNearFogPos < targetFar - 200) {
            return 0.01F;
        }
        if (currentNearFogPos < targetFar - 150) {
            return 0.02F;
        }
        if (currentNearFogPos < targetFar - 75) {
            return 0.04F;
        }
        if (currentNearFogPos < targetFar - 30) {
            return 0.8F;
        }
        return 0.16F;
    }

    /**
     * Vérifie si l'animation en cours est terminé
     * @param nearTarget Valeur proche de fin d'animation
     * @param farTarget Valeur éloignée de fin d'animation
     * @return True si l'animation du brouillard proche et éloigné est terminé, False si une des animations n'est pas terminé
     */
    public boolean isFinishAnimatedFog(float nearTarget, float farTarget) {
        isAnimatingNearFog = nearTarget != currentNearFogPos;
        isAnimatingFarFog = farTarget != currentFarFogPos;

        if (!isAnimatingNearFog && !isAnimatingFarFog) {
            isActiveAnimation = false;
            return true;
        }
        return false;
    }

    /**
     * Set toutes les valeurs à 0 quand l'animation est terminé
     */
    public void setValueForAnimationFogOff() {
        currentNearFogPos = 0.0F;
        currentFarFogPos = 0.0F;
        beginningNearFogPos = 0.0F;
        beginningFarFogPos = 0.0F;
        ObscureStarsPackets.sendToServer(new C2SSyncStatusFog(statusFog.toString()));
    }
}