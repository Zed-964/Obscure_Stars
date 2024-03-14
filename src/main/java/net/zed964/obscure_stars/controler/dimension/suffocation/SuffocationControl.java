package net.zed964.obscure_stars.controler.dimension.suffocation;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.constants.DimensionsConstants;
import net.zed964.obscure_stars.model.armors.ObscureStarsArmors;
import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusColorFog;
import net.zed964.obscure_stars.model.packets.custom.C2SSyncStatusFog;
import net.zed964.obscure_stars.utils.EventUtils;
import net.zed964.obscure_stars.utils.PLayerUtils;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationColor;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationFog;

/**
 * Contrôle de l'effet suffocation sur un joueur
 */
@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SuffocationControl {

    /**
     * Constructeur privé par défaut
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
        SuffocationFog suffocationFog = SuffocationFog.getInstance();

        if (event.getCamera().getEntity() instanceof Player
                && suffocationFog.getStatusFog() != CustomFogCapImpl.StatusDirectionCustomFog.OFF) {
            switch (suffocationFog.getStatusFog()) {
                case DECREASE -> suffocationFog.animationFogDecrease(event);
                case INCREASE -> suffocationFog.animationFogIncrease(event);
                default -> suffocationFog.animationFogFinish(event);
            }
        }
    }

    /**
     * Méthode qui change la couleur du brouillard sur le joueur lorsqu'il a l'effet suffocation
     * @param event Quand le joueur fait un rendu de couleur du brouillard
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addColorFogOnPlayer(ViewportEvent.ComputeFogColor event) {
        SuffocationColor suffocationColor = SuffocationColor.getInstance();

        if (event.getCamera().getEntity() instanceof Player
               && suffocationColor.getStatusFogColor() != CustomFogCapImpl.StatusDirectionCustomFog.OFF) {
            switch (suffocationColor.getStatusFogColor()) {
                case DECREASE -> suffocationColor.animationColorDecrease(event);
                case INCREASE -> suffocationColor.animationColorIncrease(event);
                default -> suffocationColor.animationColorFinish(event);
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void restoreValueWhenPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            Player yourself = (Player) Minecraft.getInstance().getCameraEntity();
            assert yourself != null;
            if (player.getUUID() == yourself.getUUID()) {
                SuffocationFog.getInstance().setValueForAnimationFogOff();
                ObscureStarsPackets.sendToServer(new C2SSyncStatusFog(SuffocationFog.getInstance().getStatusFog().toString()));
                SuffocationColor.getInstance().setValueForAnimationColorOff();
                ObscureStarsPackets.sendToServer(new C2SSyncStatusColorFog(SuffocationColor.getInstance().getStatusFogColor().toString()));
            }
        }
    }
}
