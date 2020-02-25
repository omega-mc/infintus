package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class UltimateBootsItem extends ArmorItem {

    public UltimateBootsItem() {
        super(Materials.ULTIMATE_ARMOR, EquipmentSlot.FEET, new Settings().group(Infintus.MAIN_GROUP));
    }
}
