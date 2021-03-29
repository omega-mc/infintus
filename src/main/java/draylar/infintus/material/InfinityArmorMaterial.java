package draylar.infintus.material;

import draylar.infintus.registry.InfintusItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class InfinityArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot equipmentSlot) {
        return 0;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot) {
        return 500;
    }

    @Override
    public int getEnchantability() {
        return 500;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(InfintusItems.INFINITY_SINGULARITY);
    }

    @Override
    public String getName() {
        return "infinity";
    }

    @Override
    public float getToughness() {
        return 500;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
