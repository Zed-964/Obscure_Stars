package net.zed964.obscure_stars;

import net.minecraftforge.common.ForgeConfigSpec;

import net.zed964.obscure_stars.constants.DimensionsConstants;

public class ObscureStarsFileConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ALLOWED_SPAWN_CUSTOM;

    public static final ForgeConfigSpec.ConfigValue<String> DIMENSION_NAME_OF_THE_SPAWN;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ALLOWED_SPAWN_WITH_STRUCTURE_CUSTOM;

    public static final ForgeConfigSpec.ConfigValue<String> STRUCTURE_FILE_NAME_OF_THE_SPAWN;

    private ObscureStarsFileConfig() {

    }

    static {

        BUILDER.push("Configs for Obscure Stars !");

        BUILDER.comment("This file is used to configure the mod Obscure Stars");

        BUILDER.pop();

        BUILDER.push("Spawn Configs :");

        ALLOWED_SPAWN_CUSTOM = BUILDER.comment("""
                        Allowed the spawn custom of the mod.
                          - True, your spawn in custom dimension
                          - False, your spawn in minecraft vanilla world""")
                .define("Allowed Spawn Custom", Boolean.TRUE);


        DIMENSION_NAME_OF_THE_SPAWN = BUILDER.comment("Name of the dimension (path + mod Id) where you appear")
                .define("Dimension Name", DimensionsConstants.ASTEROID_FIELD_DIMENSION);

        ALLOWED_SPAWN_WITH_STRUCTURE_CUSTOM = BUILDER.comment("""
                        Allowed the spawn custom with structure custom.
                          - True, your spawn in custom structure
                          - False, your spawn in platform 3x3""")
                .define("Allowed Spawn Structure Custom", Boolean.TRUE);

        // TODO change default value
        STRUCTURE_FILE_NAME_OF_THE_SPAWN = BUILDER.comment("""
                Name of the Structure file (dont forget .nbt) where you appear
                note: you can override any structure when you add a structure file with the same name of one of the file already exits on the mod""")
                .define("Structure File Name", "test_structure.nbt");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
