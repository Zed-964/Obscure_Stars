package net.zed964.obscure_stars.model.items.custom.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import net.zed964.obscure_stars.vue.armors.SpacesuitRenderer;

import org.jetbrains.annotations.NotNull;

import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Consumer;

/**
 * Class des items pour l'armure Spacesuit
 */
public class SpacesuitItem extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);

    /**
     * Constructeur par défaut de la classe
     * @param pMaterial Matériel de l'armure
     * @param pSlot Slot de l'inventaire d'un joueur
     * @param pProperties Propriété de l'item
     */
    public SpacesuitItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    /**
     * Initialization du client pour le rendu de l'armure pour chaque item équipé sur un joueur
     * @param consumer Opération
     */
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private SpacesuitRenderer spacesuitRenderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                                   EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if (this.spacesuitRenderer == null) {
                    this.spacesuitRenderer = new SpacesuitRenderer();
                }

                this.spacesuitRenderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.spacesuitRenderer;
            }
        });
    }

    /**
     * Méthode qui vérifie l'animation de l'armure
     * @param animationState Animation
     * @return Le status de l'animation
     */
    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    /**
     * Register le controller pour l'animation de l'armure
     * @param controllerRegistrar Controller
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    /**
     * Singleton pour l'animation du cache
     * @return Animation instance
     */
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }
}
