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
    CustomFogCapImpl.StatusDirectionCustomFog getStatusFog();

    /**
     * Getter de statusColor
     * @return Le status de l'animation de la couleur du brouillard
     */
    CustomFogCapImpl.StatusDirectionCustomFog getStatusColor();

    /**
     * Setter de statusFog
     * @param statusFog Status du brouillard
     */
    void setStatusFog(CustomFogCapImpl.StatusDirectionCustomFog statusFog);

    /**
     * Setter de statusColor
     * @param statusColor Status de la couleur du brouillard
     */
    void setStatusColor(CustomFogCapImpl.StatusDirectionCustomFog statusColor);
}