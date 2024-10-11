package net.zed964.obscure_stars.vue.fog.custom;

import net.minecraftforge.client.event.ViewportEvent;

import org.jetbrains.annotations.NotNull;

public interface AnimationFog {

    /**
     * Animation du brouillard lorsque le status de l'effet vaut DECREASE
     * @param event Rendu du brouillard por le client
     */
    void animationFogAppearing(ViewportEvent.RenderFog event);

    /**
     * Animation du brouillard lorsque le status de l'effet vaut INCREASE
     * @param event Rendu du brouillard por le client
     */
    void animationFogDisappearing(ViewportEvent.RenderFog event);

    /**
     * Animation du brouillard lorsque le status de l'effet vaut FINISH
     * @param event Rendu du brouillard por le client
     */
    void animationFogComplete(ViewportEvent.RenderFog event);
}
