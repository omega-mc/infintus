package draylar.infintus.material;

import draylar.infintus.registry.Items;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class UltimateArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot equipmentSlot) {
        return 10000;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot) {
        return 50;
    }

    @Override
    public int getEnchantability() {
        return 50;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.ULTIMATE_SINGULARITY);
    }

    @Override
    public String getName() {
        return "ultimate";
    }

    @Override
    public float getToughness() {
        return 10;
    }
}
