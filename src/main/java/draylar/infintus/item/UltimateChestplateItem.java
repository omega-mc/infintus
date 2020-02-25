package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class UltimateChestplateItem extends ArmorItem {

    public UltimateChestplateItem() {
        super(Materials.ULTIMATE_ARMOR, EquipmentSlot.CHEST, new Settings().group(Infintus.MAIN_GROUP));
    }
}
