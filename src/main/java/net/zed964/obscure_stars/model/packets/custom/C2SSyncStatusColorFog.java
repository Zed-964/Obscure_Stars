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
public class C2SSyncStatusColorFog {

    private final String statusColorFog;

    /**
     * Constructeur par défaut
     * @param statusColorFog Status de la couleur du brouillard
     */
    public C2SSyncStatusColorFog(String statusColorFog) {
        this.statusColorFog = statusColorFog;
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public C2SSyncStatusColorFog(FriendlyByteBuf buf) {
        this.statusColorFog = buf.readUtf();
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(statusColorFog);
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
                customFogCap.setStatusColor(CustomFogCapImpl.StatusDirectionCustomFog.valueOf(statusColorFog));
                customFogCap.saveNBTData(new CompoundTag());
            });
        });
    }
}
