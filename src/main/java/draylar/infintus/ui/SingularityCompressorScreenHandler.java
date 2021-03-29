package draylar.infintus.ui;

import draylar.infintus.Infintus;
import draylar.infintus.api.InputSlot;
import draylar.infintus.api.OutputSlot;
import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;

public class SingularityCompressorScreenHandler extends ScreenHandler {

    private SingularityCompressorBlockEntity be;

    public SingularityCompressorScreenHandler(int syncID, PlayerInventory inventory, PacketByteBuf packet) {
        super(Infintus.SINGULARITY_COMPRESSOR_HANDLER, syncID);
        BlockPos blockPos = packet.readBlockPos();
        BlockEntity potential = inventory.player.world.getBlockEntity(blockPos);

        // If the BE could not be found, close out now.
        if(!(potential instanceof SingularityCompressorBlockEntity)) {
            close(inventory.player);
            return;
        }

        be = (SingularityCompressorBlockEntity) potential;

        // compressor slots
        this.addSlot(new InputSlot(be, 44, 35));
        this.addSlot(new OutputSlot(be.output, 0, 116, 35));

        // player inventory
        int l;
        for(l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + l * 9 + 9, 8 + k * 18, 84 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
        }


        // TODO: slot with >0 count
    }

    @Override
    public ItemStack onSlotClick(int slotIndex, int data, SlotActionType actionType, PlayerEntity player) {
        // special handling for input slot
        if(slotIndex == 0) {
            Slot slot = slots.get(slotIndex);
            ItemStack input = slot.getStack();
            ItemStack cursor = player.inventory.getCursorStack();

            switch(actionType) {
                case PICKUP:
                    // If the input is air and the cursor is not, insert the cursor in.
                    if(input.isEmpty() && !cursor.isEmpty()) {
                        slot.setStack(cursor.copy());
                        cursor.setCount(0);
                        break;
                    }

                    // If the input is not air and the cursor is, try to pick up the stack.
                    if(!input.isEmpty() && cursor.isEmpty()) {
                        int amount = Math.min(64, input.getCount());
                        ItemStack toCursor = input.copy();
                        toCursor.setCount(amount);
                        input.decrement(amount);
                        player.inventory.setCursorStack(toCursor);
                        break;
                    }

                    // If the stacks match, try to fill up the input.
                    if(input.getItem().equals(cursor.getItem())) {
                        boolean match = true;
                        // if the stacks have tags, they must match
                        if(input.hasTag() != cursor.hasTag()) {
                            match = false;
                        }

                        if(input.hasTag() && cursor.hasTag() && !input.getTag().equals(cursor.getTag())) {
                            match = false;
                        }

                        if(match) {
                            int addition = cursor.getCount();
                            input.increment(addition);
                            cursor.decrement(addition);
                        }
                    }

                    // If the stacks do not match, abort mission.
                    if(input.getItem() != cursor.getItem()) {
                        break;
                    }

                    break;
                case CLONE:
                    if(player.isCreativeLevelTwoOp()) {
                        if(cursor.isEmpty()) {
                            player.inventory.setCursorStack(input.copy());
                        }
                    }

                    break;
            }

            return input;
        }
        else {
            return super.onSlotClick(slotIndex, data, actionType, player);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return Infintus.SINGULARITY_COMPRESSOR_HANDLER;
    }

    public SingularityCompressorBlockEntity getBe() {
        return be;
    }
}