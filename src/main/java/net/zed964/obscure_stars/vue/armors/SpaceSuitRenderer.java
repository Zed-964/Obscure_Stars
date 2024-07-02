package net.zed964.obscure_stars.vue.armors;

import net.zed964.obscure_stars.model.armors.custom.SpaceSuitModel;
import net.zed964.obscure_stars.model.items.custom.armor.SpaceSuitItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

/**
 * Class sui sert au rendu de l'armure Spacesuit avec Geckolib
 */
public class SpaceSuitRenderer extends GeoArmorRenderer<SpaceSuitItem> {

    /**
     * Constructeur par défaut
     */
    public SpaceSuitRenderer() {
        super(new SpaceSuitModel());
    }
}
