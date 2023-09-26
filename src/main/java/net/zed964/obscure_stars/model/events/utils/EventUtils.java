package net.zed964.obscure_stars.model.events.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public interface EventUtils {

    static boolean eventIsServerSide(EntityJoinLevelEvent event) {
        return !event.getLevel().isClientSide();
    }

    static boolean entityGoToThisDimension(EntityJoinLevelEvent event, String dimensionName) {
        return event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    static boolean entityIsNotInThisDimension(EntityJoinLevelEvent event, String dimensionName) {
        return !event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    static boolean playerHasEffectActive(ServerPlayer player, MobEffect effect) {
        return player.getEffect(effect) != null;
    }
}
