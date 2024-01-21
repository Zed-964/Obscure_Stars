package net.zed964.obscure_stars.utils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.zed964.obscure_stars.model.effects.ObscureStarsEffects;

/**
 * Class utilitaire avec des fonctions en rapport avec le joueur
 */
public class PLayerUtils {

    /**
     * Constructeur par défaut de la classe
     */
    private PLayerUtils() {

    }

    /**
     * Fonction qui vérifie si l'éffet suffocation est bien appliqué sur lui
     * @param player Joueur
     * @return True : si le joueur à l'éffet, sinon False
     */
    public static boolean hasEffectSuffocation(Player player) {
        return player.hasEffect(ObscureStarsEffects.SUFFOCATION_EFFECT.get());
    }

    /**
     * Fonction qui vérifie si le joueur à un slot d'équipement d'armure vide
     * @param player Joueur
     * @return True si le joueur à un des 4 slots d'équipements vide, sinon False
     */
    public static boolean hasEmptyEquipmentSlot(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggins = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        return helmet.isEmpty() || chestplate.isEmpty() || leggins.isEmpty() || boots.isEmpty();
    }

    /**
     * Fonction qui vérifie si le joueur à une armure d'un certain type complête
     * @param player Joueur
     * @param material Type de l'armure
     * @return True si le joueur à l'armure complête sur lui, sinon False
     */
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

