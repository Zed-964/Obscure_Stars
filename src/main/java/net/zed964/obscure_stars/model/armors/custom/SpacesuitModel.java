package net.zed964.obscure_stars.model.armors.custom;

import net.minecraft.resources.ResourceLocation;
import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.items.custom.armor.SpacesuitItem;
import software.bernie.geckolib.model.GeoModel;

/**
 * Class pour le model de l'armure grâce à Geckolib
 */
public class SpacesuitModel extends GeoModel<SpacesuitItem> {

    /**
     * Getter pour le model 3D de l'armure
     * @return Le chemin du fichier ou le model 3D de l'armure est stocké
     */
    @Override
    public ResourceLocation getModelResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "geo/spacesuit.geo.json");
    }

    /**
     * Getter pour la texture de l'armure
     * @return Le chemin du fichier ou la texture de l'armure est stocké
     */
    @Override
    public ResourceLocation getTextureResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "textures/models/armor/spacesuit.png");
    }

    /**
     * Getter pour l'animation de l'armure
     * @return Le chemin du fichier ou l'animation de l'armure est stocké
     */
    @Override
    public ResourceLocation getAnimationResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "animations/armor.animation.json");
    }
}
