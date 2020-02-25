package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class InfinityHelmetItem extends ArmorItem {

    public InfinityHelmetItem() {
        super(Materials.INFINITY_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(Infintus.MAIN_GROUP));
    }
}
