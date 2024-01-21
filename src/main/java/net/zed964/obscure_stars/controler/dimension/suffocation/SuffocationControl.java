package net.zed964.obscure_stars.controler.dimension.suffocation;

import com.mojang.blaze3d.shaders.FogShape;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.constants.DimensionsConstants;
import net.zed964.obscure_stars.model.armors.ObscureStarsArmors;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.effects.dimension.SuffocationEffect;
import net.zed964.obscure_stars.utils.EventUtils;
import net.zed964.obscure_stars.utils.PLayerUtils;
import net.zed964.obscure_stars.vue.effects.suffocation.SuffocationColor;
import net.zed964.obscure_stars.vue.effects.suffocation.SuffocationFog;

import java.util.Objects;

/**
 * Contrôle de l'effet suffocation sur un joueur
 */
@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SuffocationControl {

    /**
     * Constructeur privé, car on est sur une classe qui attrape les events
     */
    private SuffocationControl() {

    }

    /**
     * Méthode qui ajoute l'effet suffocation sur un joueur lorsqu'il rejoint une dimension
     * @param event Quand le joueur rejoint une dimension
     */
    @SubscribeEvent
    public static void addSuffocationInDimension(EntityJoinLevelEvent event) {
        if (EventUtils.entityJoinLevelIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityJoinLevelGoToDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)
                && !PLayerUtils.hasFullSetArmor(player, ObscureStarsArmors.SPACESUIT)) {
            player.addEffect(new MobEffectInstance(ObscureStarsEffects.SUFFOCATION_EFFECT.get(), 999999999));
        }
    }

    /**
     * Méthode qui supprime l'effet suffocation sur un joueur lorsqu'il rejoint une dimension
     * @param event Quand le joueur rejoint une dimension
     */
    @SubscribeEvent
    public static void removeSuffocationInDimension(EntityJoinLevelEvent event) {
        if (EventUtils.entityJoinLevelIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityJoinLevelIsNotInDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)
                && PLayerUtils.hasEffectSuffocation(player)) {
            player.removeEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
        }
    }

    /**
     * Méthode qui ajoute ou supprime l'effet suffocation lorsque le joueur change son armure
     * @param event Quand le joueur modifie son équipement
     */
    @SubscribeEvent
    public static void controlSuffocationWhenEquipmentChange(LivingEquipmentChangeEvent event) {
        // On vérifie que c'est un joueur et la dimension dans laquelle le joueur se trouve
        if (EventUtils.entityJoinLevelIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityWhenEquipmentChangeInDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)) {

            if (PLayerUtils.hasEffectSuffocation(player)) {
                // Si le joueur à toute son armure, on retire l'effet
                if (PLayerUtils.hasFullSetArmor(player, ObscureStarsArmors.SPACESUIT)) {
                    player.removeEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
                }
            } else {
                // Si le joueur à un slot d'armure vide ou que son armure n'est pas complete d'un certain type, on ajoute l'effet
                if (PLayerUtils.hasEmptyEquipmentSlot(player)
                        || !PLayerUtils.hasFullSetArmor(player, ObscureStarsArmors.SPACESUIT)) {
                    player.addEffect(new MobEffectInstance(ObscureStarsEffects.SUFFOCATION_EFFECT.get(), 999999999));
                }
            }
        }
    }

    /**
     * Méthode qui change le brouillard sur le joueur lorsqu'il a l'effet suffocation
     * @param event Quand le joueur fait un rendu de brouillard
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addFogOnPlayer(ViewportEvent.RenderFog event) {
        if (event.getCamera().getEntity() instanceof Player player
                && (PLayerUtils.hasEffectSuffocation(player))
                && Objects.requireNonNull(player.getEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get())).getEffect()
                instanceof SuffocationEffect suffocationEffect) {

            SuffocationFog suffocationFog = suffocationEffect.getSuffocationFog();

            // Ici le joueur à l'effet suffocation, on vient vérifier si l'animation du brouillard a débuté
            // si ce n'est pas le cas, on place les valeurs pour le début de l'animation
            if (!suffocationFog.getIsProcessingFog() && !suffocationFog.getIsAppliedFog()) {
                suffocationFog.setCurrentValueWhenStartingFog(event);
            }

            // on actualise l'animation du brouillard
            if (!suffocationFog.getIsAppliedFog()) {
                suffocationFog.animatingNearFog(event);
                suffocationFog.animatingFarFog(event);
                suffocationFog.animatingFinish();
            } else {
                suffocationFog.fogFinalApplied(event);
            }

            event.setFogShape(FogShape.SPHERE);
            event.setCanceled(true);
        }
    }

    /**
     * Méthode qui change al couleur du brouillard sur le joueur lorsqu'il a l'effet suffocation
     * @param event Quand le joueur fait un rendu de couleur du brouillard
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addColorFogOnPlayer(ViewportEvent.ComputeFogColor event) {
        if (event.getCamera().getEntity() instanceof Player player
                && (PLayerUtils.hasEffectSuffocation(player))
                && Objects.requireNonNull(player.getEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get())).getEffect()
                instanceof SuffocationEffect suffocationEffect) {

            SuffocationColor suffocationColor = suffocationEffect.getSuffocationColor();

            // Ici le joueur à l'effet suffocation, on vient vérifier si l'animation de la couleur du brouillard a débuté
            // si ce n'est pas le cas, on place les valeurs pour le début de l'animation
            if (!suffocationColor.getIsProcessingColor()) {
                suffocationColor.setCurrentValueWhenStartingColor(event);
            }

            // on actualise l'animation de la couleur du brouillard
            if (!suffocationColor.getIsAppliedColor()) {
                suffocationColor.setColorOnFog(event);
                suffocationColor.colorFinish();
            } else {
                suffocationColor.colorFinalApplied(event);
            }

            event.setCanceled(true);
        }
    }
}
