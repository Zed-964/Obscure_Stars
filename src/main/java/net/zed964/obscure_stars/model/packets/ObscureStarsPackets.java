package net.zed964.obscure_stars.model.packets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusColorFog;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusFog;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStatusColorFog;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStatusFog;

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

        simpleChannel.messageBuilder(C2SSyncStatusFog.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSyncStatusFog::new)
                .encoder(C2SSyncStatusFog::toBytes)
                .consumerMainThread(C2SSyncStatusFog::handle)
                .add();

        simpleChannel.messageBuilder(C2SSyncStatusColorFog.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSyncStatusColorFog::new)
                .encoder(C2SSyncStatusColorFog::toBytes)
                .consumerMainThread(C2SSyncStatusColorFog::handle)
                .add();

        simpleChannel.messageBuilder(S2CSyncStatusFog.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncStatusFog::new)
                .encoder(S2CSyncStatusFog::toBytes)
                .consumerMainThread(S2CSyncStatusFog::handle)
                .add();

        simpleChannel.messageBuilder(S2CSyncStatusColorFog.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncStatusColorFog::new)
                .encoder(S2CSyncStatusColorFog::toBytes)
                .consumerMainThread(S2CSyncStatusColorFog::handle)
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
