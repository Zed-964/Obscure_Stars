package net.zed964.obscure_stars.model.armors;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import net.zed964.obscure_stars.ObscureStars;

import org.jetbrains.annotations.NotNull;

/**
 * Enum des armures custom disponible
 */
public enum ObscureStarsArmors implements ArmorMaterial {
    SPACESUIT( new ArmorBasicProperties("spacesuit", 15, 0.0F, 0.0F),
            new int[]{2, 3, 4, 2}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, Ingredient.of(Items.STRING));

    private final ArmorBasicProperties armorBasicProperties;

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    private final int[] slotProtections;

    private final int enchantmentValue;

    private final SoundEvent sound;

    private final Ingredient repairIngredient;

    /**
     * Constructeur par défault
     * @param pArmorBasicProperties Propriétés basic de l'armure
     * @param pSlotProtections Nombre de Slot de protection
     * @param pEnchantmentValue Valeur d'enchantement
     * @param pSound Sound
     * @param pRepairIngredient Ingredient de réparation
     */
    ObscureStarsArmors(ArmorBasicProperties pArmorBasicProperties, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, Ingredient pRepairIngredient) {
        this.armorBasicProperties = pArmorBasicProperties;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.repairIngredient = pRepairIngredient;
    }

    /**
     * Getter pour la durabilité de l'item
     * @param pSlot Slot de l'inventaire du joueur
     * @return La durabilité
     */
    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.armorBasicProperties.durabilityMultiplier();
    }

    /**
     * Getter pour la défense de l'item
     * @param pSlot Slot de l'inventaire du joueur
     * @return La défense
     */
    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    /**
     * Getter pour la valeur de l'enchantement de l'armure
     * @return La valeur d'enchantement
     */
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    /**
     * Getter pour le son de l'armure
     * @return Le son
     */
    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    /**
     * Getter pour l'ingrédient de réparation de l'armure
     * @return L'ingrédient de réparation
     */
    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    /**
     * Getter pour le nom de l'amure
     * @return Le nom
     */
    @Override
    public @NotNull String getName() {
        return ObscureStars.MOD_ID + ":" + this.armorBasicProperties.name();
    }

    /**
     * Getter pour la résistance de l'armure
     * @return La résistance
     */
    @Override
    public float getToughness() {
        return this.armorBasicProperties.toughness();
    }

    /**
     * Getter pour la résistance de recul de l'armure
     * @return La résistance de recul
     */
    @Override
    public float getKnockbackResistance() {
        return this.armorBasicProperties.knockbackResistance();
    }
}
