package net.zed964.obscure_stars.vue.armors;

import net.zed964.obscure_stars.model.armors.custom.SpacesuitModel;
import net.zed964.obscure_stars.model.items.custom.armor.SpacesuitItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SpacesuitRenderer extends GeoArmorRenderer<SpacesuitItem> {

    public SpacesuitRenderer() {
        super(new SpacesuitModel());
    }
}
