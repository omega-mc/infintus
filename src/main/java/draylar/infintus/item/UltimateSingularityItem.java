package draylar.infintus.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UltimateSingularityItem extends Item {

    public UltimateSingularityItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack itemStack) {
        return true;
    }
}
