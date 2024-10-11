package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.capabilities.provider.CustomFogProvider;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Synchronisation CLient to Serveur sur le status du brouillard
 */
public class C2SSyncStateAnimationFog {

    private final String stateAnimationFog;

    /**
     * Constructeur par défaut
     * @param stateAnimationFog Status du brouillard
     */
    public C2SSyncStateAnimationFog(String stateAnimationFog) {
        this.stateAnimationFog = stateAnimationFog;
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public C2SSyncStateAnimationFog(FriendlyByteBuf buf) {
        this.stateAnimationFog = buf.readUtf();
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(stateAnimationFog);
    }

    /**
     * Action efféctué coté Serveur
     * @param supplier Context
     */
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(CustomFogProvider.PLAYER_CUSTOM_FOG).ifPresent(customFogCap -> {
                customFogCap.setStateAnimationFog(CustomFogCapImpl.StateAnimationCustomFog.valueOf(stateAnimationFog));
                customFogCap.saveNBTData(new CompoundTag());
            });
            if (Objects.equals(stateAnimationFog, CustomFogCapImpl.StateAnimationCustomFog.INACTIVE.toString())) {
                player.removeAllEffects();
            }
        });
    }
}
