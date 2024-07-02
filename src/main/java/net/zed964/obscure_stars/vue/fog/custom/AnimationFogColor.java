package net.zed964.obscure_stars.vue.fog.custom;

import net.minecraftforge.client.event.ViewportEvent;

public interface AnimationFogColor {

    /**
     * Animation de la couleur du brouillard lorsque le status de l'effet vaut DECREASE
     * @param event Rendu de la couleur du brouillard por le client
     */
    void animationColorDecrease(ViewportEvent.ComputeFogColor event);

    /**
     * Animation de la couleur du brouillard lorsque le status de l'effet vaut INCREASE
     * @param event Rendu de la couleur du brouillard por le client
     */
    void animationColorIncrease(ViewportEvent.ComputeFogColor event);

    /**
     * Animation de la couleur du brouillard lorsque le status de l'effet vaut FINISH
     * @param event Rendu de la couleur du brouillard por le client
     */
    void animationColorFinish(ViewportEvent.ComputeFogColor event);
}
