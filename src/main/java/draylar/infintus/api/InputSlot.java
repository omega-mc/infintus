package draylar.infintus.api;

import com.mojang.datafixers.util.Pair;
import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class InputSlot extends Slot {

    private final SingularityCompressorBlockEntity be;

    public InputSlot(SingularityCompressorBlockEntity be, int x, int y) {
        super(null, 0, x, y);
        this.be = be;
    }

    @Override
    public void onStackChanged(ItemStack originalItem, ItemStack itemStack) {
        int i = itemStack.getCount() - originalItem.getCount();

        if (i > 0) {
            this.onCrafted(itemStack, i);
        }
    }

    @Override
    public void onCrafted(ItemStack stack, int amount) {
    }

    @Override
    public void onTake(int amount) {
    }

    @Override
    public void onCrafted(ItemStack stack) {
    }

    @Override
    public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
        this.markDirty();
        return stack;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getStack() {
        return be.input;
    }

    @Override
    public boolean hasStack() {
        return !this.getStack().isEmpty();
    }

    @Override
    public void setStack(ItemStack stack) {
        be.input = stack;
        this.markDirty();
    }

    @Override
    public void markDirty() {
        be.markDirty();

        if(!be.getWorld().isClient) {
            be.sync();
        }
    }

    @Override
    public int getMaxItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return this.getMaxItemCount();
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    @Override
    public Pair<Identifier, Identifier> getBackgroundSprite() {
        return null;
    }

    @Override
    public ItemStack takeStack(int amount) {
        int amountTaken = Math.min(64, Math.min(be.input.getCount(), amount));
        ItemStack returned = be.input.copy();
        returned.setCount(amountTaken);
        be.input.decrement(amountTaken);
        return returned;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean doDrawHoveringEffect() {
        return true;
    }
}
