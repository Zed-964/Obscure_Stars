package net.zed964.obscure_stars.model.events.dimension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.constants.DimensionConstants;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;
import net.zed964.obscure_stars.model.events.utils.EventUtils;

@Mod.EventBusSubscriber(modid = ObscureStars.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionEvents implements EventUtils {

    private DimensionEvents() {

    }

    @SubscribeEvent
    public static void addSuffocationEffectInDimension(EntityJoinLevelEvent event) {
        if(EventUtils.eventIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityGoToThisDimension(event, DimensionConstants.ASTEROID_FIELD_PATH)) {
            player.addEffect(new MobEffectInstance(ObscureStarsEffects.TEST_EFFECT.get(), 2147483647));
        }
    }

    @SubscribeEvent
    public static void removeSuffocationEffectInDimension(EntityJoinLevelEvent event) {
        if(EventUtils.eventIsServerSide(event)
                && event.getEntity() instanceof ServerPlayer player
                && EventUtils.entityIsNotInThisDimension(event, DimensionConstants.ASTEROID_FIELD_PATH)
                && EventUtils.playerHasEffectActive(player, ObscureStarsEffects.TEST_EFFECT.get())) {
            player.removeEffect(ObscureStarsEffects.TEST_EFFECT.get());
        }
    }


}
