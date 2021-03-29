package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class InfinityPickaxeItem extends PickaxeItem {

    public InfinityPickaxeItem() {
        super(Materials.INFINITY_TOOL, -250, -2, new Item.Settings().group(Infintus.MAIN_GROUP).maxCount(1));
    }
}
