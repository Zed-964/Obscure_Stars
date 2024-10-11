package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.capabilities.provider.CustomFogProvider;

import java.util.function.Supplier;

/**
 * Synchronisation CLient to Serveur sur le status de la couleur du brouillard
 */
public class C2SSyncStateAnimationColorFog {

    private final String stateAnimationColorFog;

    /**
     * Constructeur par défaut
     * @param stateAnimationColorFog Status de la couleur du brouillard
     */
    public C2SSyncStateAnimationColorFog(String stateAnimationColorFog) {
        this.stateAnimationColorFog = stateAnimationColorFog;
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public C2SSyncStateAnimationColorFog(FriendlyByteBuf buf) {
        this.stateAnimationColorFog = buf.readUtf();
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(stateAnimationColorFog);
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
                customFogCap.setStateAnimationFogColor(CustomFogCapImpl.StateAnimationCustomFog.valueOf(stateAnimationColorFog));
                customFogCap.saveNBTData(new CompoundTag());
            });
        });
    }
}
