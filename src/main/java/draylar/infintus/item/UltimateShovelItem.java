package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.registry.Materials;
import net.minecraft.item.ShovelItem;

public class UltimateShovelItem extends ShovelItem {

    public UltimateShovelItem() {
        super(Materials.ULTIMATE_TOOL, 0, -1.6f, new Settings().group(Infintus.MAIN_GROUP));
    }
}
