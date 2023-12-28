package net.zed964.obscure_stars.vue.effects.suffocation;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.utils.MethodUtils;

public class SuffocationFog {

    private boolean isProcessingFog;

    private boolean isAppliedFog;

    private boolean isAnimatingNearFog;

    private boolean isAnimatingFarFog;

    private float currentNearPlaneDistance;

    private float currentFarPlaneDistance;

    public SuffocationFog() {
        isProcessingFog = false;
        isAppliedFog = false;
        isAnimatingNearFog = false;
        isAnimatingFarFog = false;
    }

    public void setCurrentValueWhenStartingFog(ViewportEvent.RenderFog event) {
        currentNearPlaneDistance = event.getRenderer().getRenderDistance() / 1.15F;
        currentFarPlaneDistance = event.getRenderer().getRenderDistance() * 1.225F;
        isProcessingFog = true;
    }

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

    public void fogFinalApplied(ViewportEvent.RenderFog event) {
        event.setNearPlaneDistance(EffectsConstants.SUFFOCATION_FOG_START);
        event.setFarPlaneDistance(EffectsConstants.SUFFOCATION_FOG_END);
    }

    public void animatingFinish() {
        if (!isAnimatingNearFog && !isAnimatingFarFog) {
            isProcessingFog = false;
            isAppliedFog = true;
        }
    }

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

    public void setAllToFalse() {
        isProcessingFog = false;
        isAppliedFog = false;
        isAnimatingNearFog = false;
        isAnimatingFarFog = false;
    }

    public boolean getIsProcessingFog() {
        return isProcessingFog;
    }

    public boolean getIsAppliedFog() {
        return isAppliedFog;
    }
}
