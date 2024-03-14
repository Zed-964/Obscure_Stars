package net.zed964.obscure_stars;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import net.zed964.obscure_stars.constants.ObscureStarsConstants;

import java.nio.file.Path;

public class ObscureStarsConfig {

    @Getter
    private static final ObscureStarsConfig instance = new ObscureStarsConfig();

    @Getter
    private final Path structureFolderPath;

    @Getter
    @Setter
    private ServerLevel customSpawnLevel;

    @Getter
    @Setter
    private Path structureFileName;

    private ObscureStarsConfig() {
        structureFolderPath = FileUtils.getOrCreateDirectory(
                Path.of(FMLPaths.CONFIGDIR.get() + ObscureStarsConstants.PATH_STRUCTURE_FOLDER), ObscureStarsConstants.FOLDER_NAME_STRUCTURE);
    }
}
