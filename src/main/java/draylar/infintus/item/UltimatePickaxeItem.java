package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.PickaxeItem;

public class UltimatePickaxeItem extends PickaxeItem {

    public UltimatePickaxeItem() {
        super(Materials.ULTIMATE_TOOL, 0, -2, new Settings().group(Infintus.MAIN_GROUP));
    }
}
