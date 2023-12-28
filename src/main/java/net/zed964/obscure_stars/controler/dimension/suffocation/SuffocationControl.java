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

@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SuffocationControl {

    private SuffocationControl() {

    }

    @SubscribeEvent
    public static void addSuffocationInDimension(EntityJoinLevelEvent event) {
        if (EventUtils.entityJoinLevelIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityJoinLevelGoToDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)
                && !PLayerUtils.hasFullSetArmor(player, ObscureStarsArmors.SPACESUIT)) {
            player.addEffect(new MobEffectInstance(ObscureStarsEffects.SUFFOCATION_EFFECT.get(), 999999999));
        }
    }

    @SubscribeEvent
    public static void removeSuffocationInDimension(EntityJoinLevelEvent event) {
        if (EventUtils.entityJoinLevelIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityJoinLevelIsNotInDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)
                && PLayerUtils.hasEffectSuffocation(player)) {
            player.removeEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
        }
    }

    @SubscribeEvent
    public static void controlSuffocationWhenEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityWhenEquipmentChangeInDimension(event, DimensionsConstants.ASTEROID_FIELD_PATH)) {
            if (PLayerUtils.hasEmptyEquipmentSlot(player)) {
                player.addEffect(new MobEffectInstance(ObscureStarsEffects.SUFFOCATION_EFFECT.get(), 999999999));
            } else if (PLayerUtils.hasFullSetArmor(player, ObscureStarsArmors.SPACESUIT)
                            && PLayerUtils.hasEffectSuffocation(player)) {
                player.removeEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
            } else {
                player.addEffect(new MobEffectInstance(ObscureStarsEffects.SUFFOCATION_EFFECT.get(), 999999999));
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addFogOnPlayer(ViewportEvent.RenderFog event) {
        if (event.getCamera().getEntity() instanceof Player player
                && (PLayerUtils.hasEffectSuffocation(player))
                && Objects.requireNonNull(player.getEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get())).getEffect()
                instanceof SuffocationEffect suffocationEffect) {

            SuffocationFog suffocationFog = suffocationEffect.getSuffocationFog();

            if (!suffocationFog.getIsProcessingFog() && !suffocationFog.getIsAppliedFog()) {
                suffocationFog.setCurrentValueWhenStartingFog(event);
            }

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

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addColorFogOnPlayer(ViewportEvent.ComputeFogColor event) {
        if (event.getCamera().getEntity() instanceof Player player
                && (PLayerUtils.hasEffectSuffocation(player))
                && Objects.requireNonNull(player.getEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get())).getEffect()
                instanceof SuffocationEffect suffocationEffect) {

            SuffocationColor suffocationColor = suffocationEffect.getSuffocationColor();

            if (!suffocationColor.getIsProcessingColor()) {
                suffocationColor.setCurrentValueWhenStartingColor(event);
            }

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
