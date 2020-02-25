package draylar.infintus.container;

import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import spinnery.common.BaseContainer;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;
import spinnery.widget.WWidget;

public class SingularityCompressorContainer extends BaseContainer {

    public static final int COMPRESSOR_INVENTORY = 1;
    private SingularityCompressorBlockEntity entity;

    public SingularityCompressorContainer(int syncID, PlayerInventory inventory, BlockPos pos) {
        super(syncID, inventory);

        WInterface mainInterface = new WInterface(this);
        getHolder().add(mainInterface);
        entity = ((SingularityCompressorBlockEntity) getLinkedWorld().getBlockEntity(pos));

        // register inventory
        getInventories().put(COMPRESSOR_INVENTORY, entity.baseInventory);

        // player inventory
        WSlot.addArray(mainInterface, 0,0, 9, 1);
        WSlot.addArray(mainInterface, 9, 0, 9, 3);

        // compressor slots
        WSlot.addSingle(mainInterface, 0,  1);
        WSlot.addSingle(mainInterface, 1,  1);

        // max slot count
        for (WWidget widget : mainInterface.getWidgets()) {
            if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == 1 && ((WSlot) widget).getSlotNumber() == 0) {
                ((WSlot) widget).setOverrideMaximumCount(true);
                ((WSlot) widget).setMaximumCount(1000000);
            }
        }
    }

    public int getPercentageProgress() {
        return entity.getCompressionProgress() / entity.getRequiredProgress();
    }
}