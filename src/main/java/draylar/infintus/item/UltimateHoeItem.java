package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.HoeItem;

public class UltimateHoeItem extends HoeItem {

    public UltimateHoeItem() {
        super(Materials.ULTIMATE_TOOL, -2, new Settings().group(Infintus.MAIN_GROUP));
    }
}
