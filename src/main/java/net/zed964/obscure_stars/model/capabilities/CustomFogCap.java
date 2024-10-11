package net.zed964.obscure_stars.model.capabilities;

import net.minecraft.nbt.CompoundTag;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;

/**
 * Capability du brouillard personnalisé
 */
public interface CustomFogCap {

    /**
     * Sauvegarde les données Nbt
     * @param nbt Que l'on veut sauvegarder
     */
    void saveNBTData(CompoundTag nbt);

    /**
     * Chargement des données Nbt
     * @param nbt Que l'on veut charger
     */
    void loadNBTData(CompoundTag nbt);

    /**
     * Getter de statusFog
     * @return Le status de l'animation du brouillard
     */
    CustomFogCapImpl.StateAnimationCustomFog getStateAnimationFog();

    /**
     * Getter de statusColor
     * @return Le status de l'animation de la couleur du brouillard
     */
    CustomFogCapImpl.StateAnimationCustomFog getStateAnimationFogColor();

    /**
     * Setter de statusFog
     * @param stateAnimationFog Status du brouillard
     */
    void setStateAnimationFog(CustomFogCapImpl.StateAnimationCustomFog stateAnimationFog);

    /**
     * Setter de statusColor
     * @param stateAnimationFogColor Status de la couleur du brouillard
     */
    void setStateAnimationFogColor(CustomFogCapImpl.StateAnimationCustomFog stateAnimationFogColor);
}