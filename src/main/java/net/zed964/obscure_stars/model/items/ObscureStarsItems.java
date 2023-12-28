package net.zed964.obscure_stars.model.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.armors.ObscureStarsArmors;
import net.zed964.obscure_stars.model.items.custom.armor.SpacesuitItem;

public class ObscureStarsItems {

    private ObscureStarsItems() {

    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ObscureStars.MOD_ID);

    // ----------------------------------------------------ARMOR----------------------------------------------------- //
    public static final RegistryObject<Item> SPACESUIT_HELMET = ITEMS.register("spacesuit_helmet",
            () -> new SpacesuitItem(ObscureStarsArmors.SPACESUIT, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<Item> SPACESUIT_CHESTPLATE = ITEMS.register("spacesuit_chestplate",
            () -> new SpacesuitItem(ObscureStarsArmors.SPACESUIT, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> SPACESUIT_LEGGINGS = ITEMS.register("spacesuit_leggings",
            () -> new SpacesuitItem(ObscureStarsArmors.SPACESUIT, EquipmentSlot.LEGS, new Item.Properties()));

    public static final RegistryObject<Item> SPACESUIT_BOOTS = ITEMS.register("spacesuit_boots",
            () -> new SpacesuitItem(ObscureStarsArmors.SPACESUIT, EquipmentSlot.FEET, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
