package draylar.infintus.item;

import draylar.infintus.Infintus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfinityDaggerItem extends Item {

    public InfinityDaggerItem() {
        super(new Item.Settings().group(Infintus.MAIN_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(!world.isClient) {
            HitResult location = playerEntity.rayTrace(64, 0, false);

            if(world.getBlockState(new BlockPos(location.getPos()).up()).isAir()) {
                if(world.getBlockState(new BlockPos(location.getPos()).up(2)).isAir()) {
                    playerEntity.requestTeleport(location.getPos().x, location.getPos().y + 1, location.getPos().z);
                }
            }
        }

        return super.use(world, playerEntity, hand);
    }
}
