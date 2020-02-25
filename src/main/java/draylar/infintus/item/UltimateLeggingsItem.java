package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class UltimateLeggingsItem extends ArmorItem {

    public UltimateLeggingsItem() {
        super(Materials.ULTIMATE_ARMOR, EquipmentSlot.LEGS, new Settings().group(Infintus.MAIN_GROUP));
    }
}
