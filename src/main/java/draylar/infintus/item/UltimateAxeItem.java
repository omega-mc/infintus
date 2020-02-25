package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.AxeItem;

public class UltimateAxeItem extends AxeItem {

    public UltimateAxeItem() {
        super(Materials.ULTIMATE_TOOL, 60, -2.4f, new Settings().group(Infintus.MAIN_GROUP));
    }
}
