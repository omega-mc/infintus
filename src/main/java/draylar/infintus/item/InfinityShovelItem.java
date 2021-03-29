package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class InfinityShovelItem extends ShovelItem {

    public InfinityShovelItem() {
        super(Materials.INFINITY_TOOL, 0, -1.6f, new Item.Settings().group(Infintus.MAIN_GROUP).maxCount(1));
    }
}
