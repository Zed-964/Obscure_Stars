package net.zed964.obscure_stars.model.armors;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import net.zed964.obscure_stars.ObscureStars;

import org.jetbrains.annotations.NotNull;

public enum ObscureStarsArmors implements ArmorMaterial {
    SPACESUIT( new ArmorBasicProperties("spacesuit", 15, 0.0F, 0.0F),
            new int[]{2, 3, 4, 2}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, Ingredient.of(Items.STRING));

    private final ArmorBasicProperties armorBasicProperties;

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    private final int[] slotProtections;

    private final int enchantmentValue;

    private final SoundEvent sound;

    private final Ingredient repairIngredient;

    ObscureStarsArmors(ArmorBasicProperties pArmorBasicProperties, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, Ingredient pRepairIngredient) {
        this.armorBasicProperties = pArmorBasicProperties;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.repairIngredient = pRepairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.armorBasicProperties.durabilityMultiplier();
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Override
    public @NotNull String getName() {
        return ObscureStars.MOD_ID + ":" + this.armorBasicProperties.name();
    }

    @Override
    public float getToughness() {
        return this.armorBasicProperties.toughness();
    }

    @Override
    public float getKnockbackResistance() {
        return this.armorBasicProperties.knockbackResistance();
    }
}
