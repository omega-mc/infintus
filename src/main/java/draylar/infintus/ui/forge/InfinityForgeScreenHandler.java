package draylar.infintus.ui.forge;

import draylar.infintus.Infintus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

public class InfinityForgeScreenHandler extends ScreenHandler {

    public InfinityForgeScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf packet) {
        super(Infintus.INFINITY_FORGE_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
