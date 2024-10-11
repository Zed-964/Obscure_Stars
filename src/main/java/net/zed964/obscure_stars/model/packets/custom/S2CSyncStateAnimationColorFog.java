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
public class S2CSyncStateAnimationColorFog {

    private final CustomFogCapImpl.StateAnimationCustomFog stateAnimationColorFog;

    private CustomFogCapImpl.CustomFogEffect customFogColor;

    /**
     * Constructeur par défaut
     * @param stateAnimationColorFog Status de la couleur du brouillard
     */
    public S2CSyncStateAnimationColorFog(CustomFogCapImpl.StateAnimationCustomFog stateAnimationColorFog, CustomFogColor customFogColor) {
       this.stateAnimationColorFog = stateAnimationColorFog;
       if (customFogColor instanceof SuffocationColor) {
           this.customFogColor = CustomFogCapImpl.CustomFogEffect.SUFFOCATION;
       }
    }

    /**
     * Constructeur lors de la reception d'un packet réseau
     * @param buf Données d'un packet réseau
     */
    public S2CSyncStateAnimationColorFog(FriendlyByteBuf buf) {
        this.stateAnimationColorFog = buf.readEnum(CustomFogCapImpl.StateAnimationCustomFog.class);
        this.customFogColor = buf.readEnum(CustomFogCapImpl.CustomFogEffect.class);
    }

    /**
     * Convertie l'info envoyée en bytes
     * @param buf Données d'un packet réseau
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(stateAnimationColorFog);
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
                SuffocationColor.getInstance().setStateAnimationFogColor(stateAnimationColorFog);
            }
        });
    }
}
