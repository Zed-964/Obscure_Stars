package net.zed964.obscure_stars.utils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;

public class PLayerUtils {

    private PLayerUtils() {

    }

    public static boolean hasEffectSuffocation(Player player) {
        return player.hasEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
    }

    public static boolean hasEmptyEquipmentSlot(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggins = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        return helmet.isEmpty() || chestplate.isEmpty() || leggins.isEmpty() || boots.isEmpty();
    }

    public static boolean hasFullSetArmor(Player player, ArmorMaterial material) {
        if (!hasEmptyEquipmentSlot(player)) {
            ArmorItem head = (ArmorItem) player.getInventory().getArmor(EquipmentSlot.HEAD.getIndex()).getItem();
            ArmorItem chest = (ArmorItem) player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem();
            ArmorItem legs = (ArmorItem) player.getInventory().getArmor(EquipmentSlot.LEGS.getIndex()).getItem();
            ArmorItem feet = (ArmorItem) player.getInventory().getArmor(EquipmentSlot.FEET.getIndex()).getItem();

            return head.getMaterial() == material && chest.getMaterial() == material && legs.getMaterial() == material
                    && feet.getMaterial() == material;
        } else {
            return false;
        }
    }
}

