package draylar.infintus.entity;

import draylar.infintus.registry.InfintusEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SimpleInventory;

public class InfinityForgeBlockEntity extends BlockEntity {

    private SimpleInventory inventory = new SimpleInventory(9 * 9);
    private SimpleInventory result = new SimpleInventory(1);

    public InfinityForgeBlockEntity() {
        super(InfintusEntities.INFINITY_FORGE);
    }

    public SimpleInventory getInventory() {
        return inventory;
    }

    public SimpleInventory getResult() {
        return result;
    }
}
