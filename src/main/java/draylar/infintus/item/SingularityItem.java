package draylar.infintus.item;

import draylar.infintus.Infintus;
import draylar.infintus.config.Singularity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class SingularityItem extends Item {

    private Singularity singularity;

    public SingularityItem(Singularity singularity) {
        super(new Item.Settings().group(Infintus.MAIN_GROUP));
        this.singularity = singularity;
    }

    public Singularity getSingularity() {
        return singularity;
    }
}
