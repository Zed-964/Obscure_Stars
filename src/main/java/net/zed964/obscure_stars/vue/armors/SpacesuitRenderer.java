package net.zed964.obscure_stars.vue.armors;

import net.zed964.obscure_stars.model.armors.custom.SpacesuitModel;
import net.zed964.obscure_stars.model.items.custom.armor.SpacesuitItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

/**
 * Class sui sert au rendu de l'armure Spacesuit avec Geckolib
 */
public class SpacesuitRenderer extends GeoArmorRenderer<SpacesuitItem> {

    /**
     * Constructeur par défaut
     */
    public SpacesuitRenderer() {
        super(new SpacesuitModel());
    }
}
