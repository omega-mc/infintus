package draylar.infintus.ui.forge;

import draylar.infintus.Infintus;
import draylar.infintus.api.InputSlot;
import draylar.infintus.api.OutputSlot;
import draylar.infintus.entity.InfinityForgeBlockEntity;
import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class InfinityForgeScreenHandler extends ScreenHandler {

    private CraftingInventory input;
    private CraftingResultInventory result;
    private InfinityForgeBlockEntity be;

    public InfinityForgeScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf packet) {
        super(Infintus.INFINITY_FORGE_HANDLER, syncId);
        BlockPos blockPos = packet.readBlockPos();
        BlockEntity potential = inv.player.world.getBlockEntity(blockPos);

        // If the BE could not be found, close out now.
        if(!(potential instanceof InfinityForgeBlockEntity)) {
            close(inv.player);
            return;
        }

        be = (InfinityForgeBlockEntity) potential;
        this.input = new CraftingInventory(this, 9, 9);
        this.result = new CraftingResultInventory();

        // compressor slots
        this.addSlot(new Slot(be.getResult(), 0, 210, 38));

        for(int x = 0; x < 9; x++) {
            for(int z = 0; z < 9; z++) {
                this.addSlot(new Slot(input, x * 9 + z, x * 18 + 8, z * 18 - 34));
            }
        }

        // player inventory
        int l;
        for(l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inv, k + l * 9 + 9, 8 + k * 18, 145 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inv, l, 8 + l * 18, 203));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
