package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.SwordItem;

public class UltimateSwordItem extends SwordItem {

    public UltimateSwordItem() {
        super(Materials.ULTIMATE_TOOL, 50, -2, new Settings().group(Infintus.MAIN_GROUP));
    }
}
