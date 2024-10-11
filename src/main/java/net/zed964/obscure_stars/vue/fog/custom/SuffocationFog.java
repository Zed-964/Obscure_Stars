package net.zed964.obscure_stars.vue.fog.custom;

import com.mojang.blaze3d.shaders.FogShape;

import lombok.Getter;
import net.minecraftforge.client.event.ViewportEvent;

import net.zed964.obscure_stars.constants.EffectsConstants;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStateAnimationFog;
import net.zed964.obscure_stars.utils.MethodUtils;
import net.zed964.obscure_stars.vue.fog.CustomFog;

public class SuffocationFog extends CustomFog implements AnimationFog {

    @Getter
    private static final SuffocationFog instance = new SuffocationFog();

    private SuffocationFog() {

    }

    // --- APPLY EFFECT --- //
    // set effect -> status == DECREASE
    // Animation fog increase begin :: animation finish -> status == FINISH
    // if player set full armor -> loose effect                             /!\ GENERIC EVENT -> Watcher player armor DONE
    // if player death -> set all to default                                /!\ GENERIC EVENT -> DONE
    // if player go new level -> verify level and set or loose effect       /!\ GENERIC EVENT -> Watcher player new level DONE


    // --- REMOVE EFFECT --- //
    // loose effect -> status == INCREASE
    // Animation fog decrease begin :: animation finish -> status == OFF
    // if player leave full armor -> apply effect                           /!\ GENERIC EVENT -> Watcher player armor DONE
    // if player death -> set all to default                                /!\ GENERIC EVENT -> DONE
    // if player go new level -> verify level and set or loose effect       /!\ GENERIC EVENT -> /!\ GENERIC EVENT -> Watcher player new level DONE

    // VERIFY LOGIC
    // msg in chat -> Clear fog + color
    // --> feature on / off
    // first -> Status fog
    // second value reset
    // third value when player move equipment

    // SYNC CLient -> Serveur DONE but not verify
    // Sync Client <- Serveur  ??

    @Override
    public void animationFogAppearing(ViewportEvent.RenderFog event) {
        currentNearFogPos += 2 * speedAnimationFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END);
        currentFarFogPos += speedAnimationFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END);

        currentNearFogPos =  MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_NEAR_END, currentNearFogPos);
        currentFarFogPos = MethodUtils.keepMaxValue(EffectsConstants.SUFFOCATION_FOG_FAR_END, currentFarFogPos);

        event.setNearPlaneDistance(currentNearFogPos);
        event.setFarPlaneDistance(currentFarFogPos);

        if (isCompleteAnimatedFog(EffectsConstants.SUFFOCATION_FOG_NEAR_END, EffectsConstants.SUFFOCATION_FOG_FAR_END)) {
            stateAnimationFog = CustomFogCapImpl.StateAnimationCustomFog.COMPLETE;
            ObscureStarsPackets.sendToServer(new C2SSyncStateAnimationFog(stateAnimationFog.toString()));
        }

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    @Override
    public void animationFogDisappearing(ViewportEvent.RenderFog event) {
        currentNearFogPos += speedAnimationFog(beginningNearFogPos, beginningFarFogPos);
        currentFarFogPos += 2 * speedAnimationFog(beginningNearFogPos, beginningFarFogPos);

        currentNearFogPos = MethodUtils.keepMinValue(beginningNearFogPos, currentNearFogPos);
        currentFarFogPos = MethodUtils.keepMinValue(beginningFarFogPos, currentFarFogPos);

        event.setFarPlaneDistance(currentFarFogPos);
        event.setNearPlaneDistance(currentNearFogPos);

        if (isCompleteAnimatedFog(beginningNearFogPos, beginningFarFogPos)) {
            setValueForAnimationFogInactive();
        }

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    @Override
    public void animationFogComplete(ViewportEvent.RenderFog event) {
        event.setNearPlaneDistance(EffectsConstants.SUFFOCATION_FOG_NEAR_END);
        event.setFarPlaneDistance(EffectsConstants.SUFFOCATION_FOG_FAR_END);

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }
}
