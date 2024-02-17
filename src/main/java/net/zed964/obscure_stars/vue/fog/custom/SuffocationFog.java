package net.zed964.obscure_stars.vue.fog.custom;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraftforge.client.event.ViewportEvent;
import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusFog;
import net.zed964.obscure_stars.utils.MethodUtils;
import net.zed964.obscure_stars.vue.fog.CustomFog;
import org.jetbrains.annotations.NotNull;

public class SuffocationFog extends CustomFog {

    /**
     * Animation du brouillard lorsque le status de l'effet Suffocation vaut DECREASE
     * @param event Rendu du brouillard por le client
     */
    public static void animationFogDecrease(ViewportEvent.RenderFog event) {
        if (!isActiveAnimation) {
            setBeginningValueWhenStartingFog(event.getRenderer());
            isActiveAnimation = true;
            isAnimatingNearFog = true;
            isAnimatingFarFog = true;
        }

        currentNearFogPos += 2 * speedAnimationFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END);
        currentFarFogPos += speedAnimationFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END);

        currentNearFogPos = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_NEAR_END, currentNearFogPos);
        currentFarFogPos = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_FAR_END, currentFarFogPos);

        event.setNearPlaneDistance(currentNearFogPos);
        event.setFarPlaneDistance(currentFarFogPos);

        if (isFinishAnimatedFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END)) {
            statusFog = CustomFogCapImpl.StatusDirectionCustomFog.FINISH;
            ObscureStarsPackets.sendToServer(new C2SSyncStatusFog(statusFog.toString()));
        }

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    /**
     * Animation du brouillard lorsque le status de l'effet Suffocation vaut INCREASE
     * @param event Rendu du brouillard por le client
     */
    public static void animationFogIncrease(ViewportEvent.RenderFog event) {
        if (!isActiveAnimation) {
            isActiveAnimation = true;
            isAnimatingNearFog = true;
            isAnimatingFarFog = true;
        }

        currentNearFogPos += speedAnimationFog(beginningNearFogPos, beginningFarFogPos);
        currentFarFogPos += 2 * speedAnimationFog(beginningNearFogPos, beginningFarFogPos);

        currentNearFogPos = MethodUtils.keepMinValue(beginningNearFogPos, currentNearFogPos);
        currentFarFogPos = MethodUtils.keepMinValue(beginningFarFogPos, currentFarFogPos);

        event.setFarPlaneDistance(currentFarFogPos);
        event.setNearPlaneDistance(currentNearFogPos);

        if (isFinishAnimatedFog(beginningNearFogPos, beginningFarFogPos)) {
            statusFog = CustomFogCapImpl.StatusDirectionCustomFog.OFF;
            setValueForAnimationFogOff();
            ObscureStarsPackets.sendToServer(new C2SSyncStatusFog(statusFog.toString()));
        }

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    /**
     * Animation du brouillard lorsque le status de l'effet Suffocation vaut FINISH
     * @param event Rendu du brouillard por le client
     */
    public static void animationFogFinish(ViewportEvent.@NotNull RenderFog event) {
        event.setNearPlaneDistance(EffectsConstants.SUFFOCATION_FOG_NEAR_END);
        event.setFarPlaneDistance(EffectsConstants.SUFFOCATION_FOG_FAR_END);

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }
}
