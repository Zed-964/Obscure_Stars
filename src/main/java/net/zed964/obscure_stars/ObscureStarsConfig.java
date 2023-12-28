package net.zed964.obscure_stars;

import net.minecraft.server.level.ServerLevel;

public class ObscureStarsConfig {

    private ObscureStarsConfig() {

    }

    private static ServerLevel customSpawnLevel;

    public static ServerLevel getSpawnLevel() {
        return customSpawnLevel;
    }

    public static void setSpawnLevel(ServerLevel level) {
        customSpawnLevel = level;
    }
}
