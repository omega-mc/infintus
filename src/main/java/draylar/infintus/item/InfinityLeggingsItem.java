package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class InfinityLeggingsItem extends ArmorItem {

    public InfinityLeggingsItem() {
        super(Materials.INFINITY_ARMOR, EquipmentSlot.LEGS, new Settings().group(Infintus.MAIN_GROUP));
    }
}
