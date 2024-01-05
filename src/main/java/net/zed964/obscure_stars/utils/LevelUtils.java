package net.zed964.obscure_stars.utils;

import net.minecraft.server.level.ServerLevel;

public class LevelUtils {

    private LevelUtils() {

    }

    public static String getFullNameOfLevel(ServerLevel level) {
        return level.dimension().location().getNamespace() + ":" + level.dimension().location().getPath();
    }
}
