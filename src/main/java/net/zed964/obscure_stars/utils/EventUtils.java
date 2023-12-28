package net.zed964.obscure_stars.utils;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public class EventUtils {

    private EventUtils() {

    }

    public static boolean entityJoinLevelIsServerSide(EntityJoinLevelEvent event) {
        return !event.getLevel().isClientSide();
    }

    public static boolean entityJoinLevelGoToDimension(EntityJoinLevelEvent event, String dimensionName) {
        return event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    public static boolean entityJoinLevelIsNotInDimension(EntityJoinLevelEvent event, String dimensionName) {
        return !event.getLevel().dimension().location().getPath().equals(dimensionName);
    }

    public static boolean entityWhenEquipmentChangeInDimension(LivingEquipmentChangeEvent event, String dimensionName) {
        return event.getEntity().getLevel().dimension().location().getPath().equals(dimensionName);
    }
}
