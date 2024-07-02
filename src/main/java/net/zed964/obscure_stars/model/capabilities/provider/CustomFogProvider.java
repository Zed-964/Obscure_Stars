package net.zed964.obscure_stars.model.capabilities.provider;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import net.zed964.obscure_stars.model.capabilities.CustomFogCap;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Class qui gère une capability pour le brouillard custom associé au joueur
 */
public class CustomFogProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<CustomFogCap> PLAYER_CUSTOM_FOG = CapabilityManager.get(new CapabilityToken<>() {
    });

    private CustomFogCap customFogCap = null;

    private final LazyOptional<CustomFogCap> optional = LazyOptional.of(this::createCustomFog);

    /**
     * Renvoie l'instance de customFogCap ou la créer si elle n'existe pas
     * @return Une instance de la class CustomFogCap
     */
    private CustomFogCap createCustomFog() {
        if (this.customFogCap == null) {
            this.customFogCap = new CustomFogCapImpl();
        }
        return this.customFogCap;
    }

    /**
     * Obtient la capability sur le player
     * @param cap The capability to check
     * @param side The Side to check from,
     *   <strong>CAN BE NULL</strong>. Null is defined to represent 'internal' or 'self'
     * @return Un objet qui contient la capability du custom fog ou, un objet vide
     * @param <T> Type de la capability
     */
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_CUSTOM_FOG) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    /**
     * Serialize le Nbt
     * @return nbt de la capability CustomFog
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCustomFog().saveNBTData(nbt);
        return nbt;
    }

    /**
     * Deserialize le Nbt
     * @param nbt de la capability CustomFog
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCustomFog().loadNBTData(nbt);
    }
}
