package draylar.infintus.block;

import draylar.infintus.entity.InfinityForgeBlockEntity;
import draylar.infintus.ui.compressor.SingularityCompressorScreenHandler;
import draylar.infintus.ui.forge.InfinityForgeScreenHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InfinityForgeBlock extends Block implements BlockEntityProvider {

    public InfinityForgeBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new InfinityForgeBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            player.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                    buf.writeLong(pos.asLong());
                }

                @Override
                public Text getDisplayName() {
                    return new LiteralText("Singularity Compressor");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
                    packet.writeLong(pos.asLong());
                    return new InfinityForgeScreenHandler(syncId, inv, packet);
                }
            });
        }

        return ActionResult.SUCCESS;
    }
}
