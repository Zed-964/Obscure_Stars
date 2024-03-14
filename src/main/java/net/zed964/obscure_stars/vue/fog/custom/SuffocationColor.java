package net.zed964.obscure_stars.vue.fog.custom;

import lombok.Getter;

import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusColorFog;
import net.zed964.obscure_stars.utils.MethodUtils;
import net.zed964.obscure_stars.vue.fog.CustomFogColor;

public class SuffocationColor extends CustomFogColor implements AnimationFogColor {

    @Getter
    private static final SuffocationColor instance = new SuffocationColor();

    private SuffocationColor() {

    }

    @Override
    public void animationColorDecrease(ViewportEvent.ComputeFogColor event) {
        if (!isAnimatingColor) {
            setBeginningValueWhenStartingColor(event);
            isAnimatingColor = true;
        }

        currentRed += speedAnimationColor(EffectsConstants.SUFFOCATION_FOG_COLOR_RED_END, currentRed);
        currentBlue += speedAnimationColor(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE_END, currentBlue);
        currentGreen += speedAnimationColor(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN_END, currentGreen);

        currentRed = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_RED_END, currentRed);
        currentBlue = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE_END, currentBlue);
        currentGreen = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN_END, currentGreen);

        event.setRed(currentRed);
        event.setBlue(currentBlue);
        event.setGreen(currentGreen);

        if (isFinishAnimatedColor(EffectsConstants.SUFFOCATION_FOG_COLOR_RED_END, EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE_END, EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN_END)) {
            statusFogColor = CustomFogCapImpl.StatusDirectionCustomFog.FINISH;
            ObscureStarsPackets.sendToServer(new C2SSyncStatusColorFog(statusFogColor.toString()));
        }

        event.setCanceled(true);
    }

    @Override
    public void animationColorIncrease(ViewportEvent.ComputeFogColor event) {
        isAnimatingColor = true;

        currentRed += speedAnimationColor(beginningRed, currentRed);
        currentBlue += speedAnimationColor(beginningBlue, currentBlue);
        currentGreen += speedAnimationColor(beginningGreen, currentGreen);

        currentRed = MethodUtils.keepMinValue(beginningRed, currentRed);
        currentBlue = MethodUtils.keepMinValue(beginningBlue, currentBlue);
        currentGreen = MethodUtils.keepMinValue(beginningGreen, currentGreen);

        event.setRed(currentRed);
        event.setBlue(currentBlue);
        event.setGreen(currentGreen);

        if (isFinishAnimatedColor(beginningRed, beginningBlue, beginningGreen)) {
            setValueForAnimationColorOff();
            ObscureStarsPackets.sendToServer(new C2SSyncStatusColorFog(statusFogColor.toString()));
        }

        event.setCanceled(true);
    }

    @Override
    public void animationColorFinish(ViewportEvent.ComputeFogColor event) {
        event.setRed(EffectsConstants.SUFFOCATION_FOG_COLOR_RED_END);
        event.setBlue(EffectsConstants.SUFFOCATION_FOG_COLOR_BLUE_END);
        event.setGreen(EffectsConstants.SUFFOCATION_FOG_COLOR_GREEN_END);

        event.setCanceled(true);
    }
}
