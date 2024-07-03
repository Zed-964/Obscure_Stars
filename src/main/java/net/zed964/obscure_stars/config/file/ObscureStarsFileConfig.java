package net.zed964.obscure_stars.config.file;

import net.minecraftforge.common.ForgeConfigSpec;
import net.zed964.obscure_stars.constants.DimensionsConstants;

import java.util.List;

/**
 * Class pour gérer le fichier de conf du mod
 */
public class ObscureStarsFileConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    public static final String FILE_NAME_CONFIG = "obscure-stars-config.toml";

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DIMENSIONS_HAS_SUFFOCATION_EFFECT;

    /**
     * Constructeur privé par défaut
     */
    private ObscureStarsFileConfig(){

    }

    static {
        BUILDER.push("Configs for Obscure Stars !");

        BUILDER.comment("This file is used to configure the mod Obscure Stars");

        BUILDER.pop();

        BUILDER.push("Effect Configs : ");

        DIMENSIONS_HAS_SUFFOCATION_EFFECT = BUILDER.comment("""
                Here, you can add all the dimensions you want to have the suffocation effect in.
                    Warning : you need to add the name of the dimension with his namespace i.e. "minecraft:the_end"
                """).defineList("List of dimensions that have the suffocation effect", List.of(DimensionsConstants.ASTEROID_FIELD_DIMENSION), entry -> true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
