package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class UltimateHelmetItem extends ArmorItem {

    public UltimateHelmetItem() {
        super(Materials.ULTIMATE_ARMOR, EquipmentSlot.HEAD, new Settings().group(Infintus.MAIN_GROUP));
    }
}
