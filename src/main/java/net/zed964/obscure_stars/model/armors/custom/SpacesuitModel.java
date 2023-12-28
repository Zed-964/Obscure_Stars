package net.zed964.obscure_stars.model.armors.custom;

import net.minecraft.resources.ResourceLocation;
import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.items.custom.armor.SpacesuitItem;
import software.bernie.geckolib.model.GeoModel;

public class SpacesuitModel extends GeoModel<SpacesuitItem> {

    @Override
    public ResourceLocation getModelResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "geo/spacesuit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "textures/models/armor/spacesuit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpacesuitItem animatable) {
        return new ResourceLocation(ObscureStars.MOD_ID, "animations/armor.animation.json");
    }
}
