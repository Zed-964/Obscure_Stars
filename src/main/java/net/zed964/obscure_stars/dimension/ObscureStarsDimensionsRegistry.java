package net.zed964.obscure_stars.dimension;

import lombok.extern.slf4j.Slf4j;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import net.zed964.obscure_stars.ObscureStars;

@Slf4j
public class ObscureStarsDimensionsRegistry {

    public static final ResourceKey<Level> ASTEROID_FIELD = ResourceKey.create(Registries.DIMENSION,
           new ResourceLocation(ObscureStars.MOD_ID, "asteroid_field"));
//
    public static final ResourceKey<DimensionType> ASTEROID_FIELD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ASTEROID_FIELD.location());

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
        log.info("Registering ObscureStars Dimension");
    }

}
