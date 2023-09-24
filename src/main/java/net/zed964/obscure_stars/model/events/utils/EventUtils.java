package net.zed964.obscure_stars.model.events.utils;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public class EventUtils {

    private EventUtils() {

    }

    public static boolean verifyEventIsServerSide(EntityJoinLevelEvent event) {
        return !event.getLevel().isClientSide();
    }

    public static boolean verifyEventFromDimension(EntityJoinLevelEvent event, String dimensionName) {
        return event.getLevel().dimension().location().getPath().equals(dimensionName);
    }
}
