package net.zed964.obscure_stars.model.packets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStateAnimationColorFog;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStateAnimationFog;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStateAnimationColorFog;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStateAnimationFog;

/**
 * Gére tous les packets personnalisés du mod
 */
public class ObscureStarsPackets {
    private static SimpleChannel instance;

    private static int packetId = 0;

    private ObscureStarsPackets() {

    }

    /**
     * Incrémente un Id
     * @return Id incrémenter
     */
    private static int id() {
        return packetId++;
    }

    /**
     * Enregistre tous les différents packets/message custom du mod
     */
    public static void register() {
        SimpleChannel simpleChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ObscureStars.MOD_ID, "packets"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        instance = simpleChannel;

        simpleChannel.messageBuilder(C2SSyncStateAnimationFog.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSyncStateAnimationFog::new)
                .encoder(C2SSyncStateAnimationFog::toBytes)
                .consumerMainThread(C2SSyncStateAnimationFog::handle)
                .add();

        simpleChannel.messageBuilder(C2SSyncStateAnimationColorFog.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSyncStateAnimationColorFog::new)
                .encoder(C2SSyncStateAnimationColorFog::toBytes)
                .consumerMainThread(C2SSyncStateAnimationColorFog::handle)
                .add();

        simpleChannel.messageBuilder(S2CSyncStateAnimationFog.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncStateAnimationFog::new)
                .encoder(S2CSyncStateAnimationFog::toBytes)
                .consumerMainThread(S2CSyncStateAnimationFog::handle)
                .add();

        simpleChannel.messageBuilder(S2CSyncStateAnimationColorFog.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncStateAnimationColorFog::new)
                .encoder(S2CSyncStateAnimationColorFog::toBytes)
                .consumerMainThread(S2CSyncStateAnimationColorFog::handle)
                .add();
    }

    /**
     * Envoi un message au serveur
     * @param msg Packets custom
     * @param <T> Class Custom d'un message
     */
    public  static  <T> void sendToServer(T msg) {
        instance.sendToServer(msg);
    }

    /**
     * Envoi un message à un client spécifique
     * @param msg Packets custom
     * @param player Un Joueur
     * @param <T> Class Custom d'un message
     */
    public static <T> void sendToPlayer(T msg, ServerPlayer player) {
        instance.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
