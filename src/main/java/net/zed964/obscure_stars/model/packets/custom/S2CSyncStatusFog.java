package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.vue.fog.CustomFog;

import java.util.function.Supplier;

/**
 * Synchronisation Serveur to CLient sur le status du brouillard
 */
public class S2CSyncStatusFog {
    
    private final String statusFog;

    /**
     * Constructeur par défaut
     * @param statusFog Status du brouillard
     */
    public S2CSyncStatusFog(String statusFog) {
       this.statusFog = statusFog;
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public S2CSyncStatusFog(FriendlyByteBuf buf) {
        this.statusFog = buf.readUtf();
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(statusFog);
    }

    /**
     * Action efféctué coté Client
     * @param supplier Context
     */
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> CustomFog.setStatusFog(CustomFogCapImpl.StatusDirectionCustomFog.valueOf(statusFog)));
    }
}
