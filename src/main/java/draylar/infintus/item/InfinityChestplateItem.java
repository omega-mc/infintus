package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class InfinityChestplateItem extends ArmorItem {

    public InfinityChestplateItem() {
        super(Materials.INFINITY_ARMOR, EquipmentSlot.CHEST, new Settings().group(Infintus.MAIN_GROUP));
    }
}
