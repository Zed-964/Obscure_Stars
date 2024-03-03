package net.zed964.obscure_stars.model.packets.custom;

import net.minecraft.network.FriendlyByteBuf;

import net.minecraftforge.network.NetworkEvent;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.vue.fog.CustomFogColor;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationColor;

import java.util.function.Supplier;

/**
 * Synchronisation Serveur to CLient sur le status de la couleur du brouillard
 */
public class S2CSyncStatusColorFog {

    private final CustomFogCapImpl.StatusDirectionCustomFog statusColorFog;

    private CustomFogCapImpl.CustomFogEffect customFogColor;

    /**
     * Constructeur par défaut
     * @param statusColorFog Status de la couleur du brouillard
     */
    public S2CSyncStatusColorFog(CustomFogCapImpl.StatusDirectionCustomFog statusColorFog, CustomFogColor customFogColor) {
       this.statusColorFog = statusColorFog;
       if (customFogColor instanceof SuffocationColor) {
           this.customFogColor = CustomFogCapImpl.CustomFogEffect.SUFFOCATION;
       }
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public S2CSyncStatusColorFog(FriendlyByteBuf buf) {
        this.statusColorFog = buf.readEnum(CustomFogCapImpl.StatusDirectionCustomFog.class);
        this.customFogColor = buf.readEnum(CustomFogCapImpl.CustomFogEffect.class);
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(statusColorFog);
        buf.writeEnum(customFogColor);
    }

    /**
     * Action efféctué coté Client
     * @param supplier Context
     */
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (customFogColor == CustomFogCapImpl.CustomFogEffect.SUFFOCATION) {
                SuffocationColor.getInstance().setStatusFogColor(statusColorFog);
            }
        });
    }
}
