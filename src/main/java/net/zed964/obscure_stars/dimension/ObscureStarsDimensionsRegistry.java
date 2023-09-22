package net.zed964.obscure_stars.dimension;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.zed964.obscure_stars.ObscureStars;
import org.slf4j.Logger;

import java.awt.*;

public class ObscureStarsDimensionsRegistry {

    private static final Logger LOGGER = LogUtils.getLogger();

//    public static final ResourceKey<Level> ASTEROID_FIELD = ResourceKey.create(Registry.DIMENSION_REGISTRY,
//            new ResourceLocation(ObscureStars.MOD_ID, "asteroid_field"));
//
//    public static final ResourceKey<DimensionType> ASTEROID_FIELD_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, ASTEROID_FIELD.location());

    /*TODO
    Ambient sound ?
    Mood sound ?
    Additions sound ?
    Music ?
    Particle ?
    */

//    Dimension;
//    DimensionTypes
    public static void register() {
        LOGGER.info("Registering ObscureStars Dimension");
    }

}
