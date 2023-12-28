package net.zed964.obscure_stars.vue.effects.suffocation;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.utils.MethodUtils;

public class SuffocationColor {

    private boolean isIsProcessingColor;

    private boolean isAppliedColor;

    private float currentRed;

    private float currentGreen;

    private float currentBlue;

    public SuffocationColor() {
        isIsProcessingColor = false;
        isAppliedColor = false;
    }

    public void setCurrentValueWhenStartingColor(ViewportEvent.ComputeFogColor event) {
        currentRed = event.getRed();
        currentGreen = event.getGreen();
        currentBlue = event.getBlue();
        isIsProcessingColor = true;
    }

    public void colorFinalApplied(ViewportEvent.ComputeFogColor event) {
        event.setRed(EffectsConstants.SUFFOCATION_FOG_COLOR_RED);
        event.setGreen(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN);
        event.setBlue(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE);
    }

    public void colorFinish() {
        if (currentRed == EffectsConstants.SUFFOCATION_FOG_COLOR_RED
                && currentGreen == EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN
                && currentBlue == EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE) {
            isIsProcessingColor = false;
            isAppliedColor = true;
        }
    }

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

    public void setAllToFalse() {
        isIsProcessingColor = false;
        isAppliedColor = false;
    }

    public boolean getIsProcessingColor() {
        return isIsProcessingColor;
    }

    public boolean getIsAppliedColor() {
        return isAppliedColor;
    }
}
