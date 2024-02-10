package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.vue.fog.CustomFogColor;

import java.util.function.Supplier;

/**
 * Synchronisation Serveur to CLient sur le status de la couleur du brouillard
 */
public class S2CSyncStatusColorFog {

    private final String statusColorFog;

    /**
     * Constructeur par défaut
     * @param statusColorFog Status de la couleur du brouillard
     */
    public S2CSyncStatusColorFog(String statusColorFog) {
       this.statusColorFog = statusColorFog;
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public S2CSyncStatusColorFog(FriendlyByteBuf buf) {
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
     * Action efféctué coté Client
     * @param supplier Context
     */
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> CustomFogColor.setStatusFogColor(CustomFogCapImpl.StatusDirectionCustomFog.valueOf(statusColorFog)));
    }
}
