package net.zed964.obscure_stars.model.armors;

/**
 * Record Custom pour les propriétés d'une armure
 * @param name Nom de l'armure
 * @param durabilityMultiplier Multiplicateur de durabilité de l'armure
 * @param toughness Résistance de l'armure
 * @param knockbackResistance Résistance du recul de l'armure
 */
public record ArmorBasicProperties(String name, int durabilityMultiplier, float toughness, float knockbackResistance) {

}
