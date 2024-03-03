package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.vue.fog.CustomFog;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationFog;

import java.util.function.Supplier;

/**
 * Synchronisation Serveur to CLient sur le status du brouillard
 */
public class S2CSyncStatusFog {
    
    private final CustomFogCapImpl.StatusDirectionCustomFog statusFog;

    private CustomFogCapImpl.CustomFogEffect customFog;


    /**
     * Constructeur par défaut
     * @param statusFog Status du brouillard
     */
    public S2CSyncStatusFog(CustomFogCapImpl.StatusDirectionCustomFog statusFog, CustomFog customFog) {
       this.statusFog = statusFog;
       if (customFog instanceof SuffocationFog) {
           this.customFog = CustomFogCapImpl.CustomFogEffect.SUFFOCATION;
       }
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public S2CSyncStatusFog(FriendlyByteBuf buf) {
        this.statusFog = buf.readEnum(CustomFogCapImpl.StatusDirectionCustomFog.class);
        this.customFog = buf.readEnum(CustomFogCapImpl.CustomFogEffect.class);
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(statusFog);
        buf.writeEnum(customFog);
    }

    /**
     * Action efféctué coté Client
     * @param supplier Context
     */
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (customFog == CustomFogCapImpl.CustomFogEffect.SUFFOCATION) {
                SuffocationFog.getInstance().setStatusFog(statusFog);
            }
        });
    }
}
