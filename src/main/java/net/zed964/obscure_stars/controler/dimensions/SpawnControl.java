package net.zed964.obscure_stars.controler.dimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.ObscureStarsConfig;
import net.zed964.obscure_stars.ObscureStarsFileConfig;
import net.zed964.obscure_stars.exceptions.ExceptionEnum;
import net.zed964.obscure_stars.exceptions.custom.configuration.BadConfigurationException;
import net.zed964.obscure_stars.exceptions.custom.configuration.DimensionSpawnNotFoundException;
import net.zed964.obscure_stars.model.dimensions.SpawnCustom;
import net.zed964.obscure_stars.utils.LevelUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnControl {

    private SpawnControl() {

    }

    @SubscribeEvent
    public static void verifySpawnDimensionCustom(ServerStartingEvent event) throws BadConfigurationException {
        if (Boolean.TRUE.equals(ObscureStarsFileConfig.ALLOWED_SPAWN_CUSTOM.get())) {
            MinecraftServer server = event.getServer();
            for (ServerLevel level : server.getAllLevels()) {
                if (LevelUtils.getFullNameOfLevel(level).equals(ObscureStarsFileConfig.DIMENSION_NAME_OF_THE_SPAWN.get())) {
                    ObscureStarsConfig.getInstance().setCustomSpawnLevel(level);
                    return;
                }
            }
            throw new BadConfigurationException(ExceptionEnum.BAD_CONFIGURATION_DEFAULT, new DimensionSpawnNotFoundException(ExceptionEnum.SPAWN_DIMENSION_NOT_FOUND));
        }
    }

    @SubscribeEvent
    public static void verifySpawnStructureCustom(ServerStartingEvent event) throws BadConfigurationException {
        if (Boolean.TRUE.equals(ObscureStarsFileConfig.ALLOWED_SPAWN_CUSTOM.get())
            && Boolean.TRUE.equals(ObscureStarsFileConfig.ALLOWED_SPAWN_WITH_STRUCTURE_CUSTOM.get())) {

            File structureCustom = ObscureStarsConfig.getInstance().getStructureFolderPath().toFile();
            File[] listOfFiles = structureCustom.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.getName().equals(ObscureStarsFileConfig.STRUCTURE_FILE_NAME_OF_THE_SPAWN.get()))  {
                        ObscureStarsConfig.getInstance().setStructureFileName(Path.of(file.getPath()));
                        break;
                    }
                }
            }

            throw new BadConfigurationException(ExceptionEnum.BAD_CONFIGURATION_DEFAULT, new DimensionSpawnNotFoundException(ExceptionEnum.SPAWN_STRUCTURE_FILE_NOT_FOUND));
        }
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public static void controlSpawnNewPlayerInMultiplayer(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        String uuid = player.getStringUUID();
        File file = player.server.getFile(LevelResource.PLAYER_DATA_DIR + "\\" + uuid + ".dat");
        if (!file.canRead()) {
            player.teleportTo(ObscureStarsConfig.getInstance().getCustomSpawnLevel(), player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
            SpawnCustom.verifySpawn(event);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void controlSpawnNewPlayerInSinglePlayer(PlayerEvent.PlayerLoggedInEvent event) {
        if (Minecraft.getInstance().isSingleplayer()) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            String uuid = player.getStringUUID();
            File file = Objects.requireNonNull(player.getServer())
                    .getFile(player.getServer().getWorldPath(LevelResource.PLAYER_DATA_DIR) + "\\" + uuid + ".dat");
            if (!file.canRead()) {
                player.teleportTo(ObscureStarsConfig.getInstance().getCustomSpawnLevel(), player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
                SpawnCustom.verifySpawn(event);
            }
        }
    }
}