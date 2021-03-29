package draylar.infintus.entity;

import draylar.infintus.block.SingularityCompressorBlock;
import draylar.infintus.config.Singularity;
import draylar.infintus.registry.InfintusEntities;
import draylar.infintus.registry.InfintusItems;
import draylar.infintus.util.SingularityUtils;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.registry.Registry;

public class SingularityCompressorBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, InventoryChangedListener {

    public ItemStack input = ItemStack.EMPTY;
    public SimpleInventory output = new SimpleInventory(1);

    private boolean isCompressing;
    private int compressionProgress = 0;
    private static final int REQUIRED_PROGRESS = 20 * 10;

    public SingularityCompressorBlockEntity() {
        super(InfintusEntities.SINGULARITY_COMPRESSOR);
    }

    @Override
    public void tick() {
        if(world != null && !world.isClient) {
            tickCompression();
        }
    }

    public void tickCompression() {
        assert this.world != null;

        // not compressing currently, check if we can
        if(!isCompressing) {

            // check if the current item is valid for compression
            if(SingularityUtils.isValidMaterial(input.getItem())) {

                // get the resulting singularity
                Singularity result = SingularityUtils.getCompressionResult(input.getItem());

                // check if we have the required count & there is nothing in the output
                if(input.getCount() >= result.getCost() && output.isEmpty()) {
                    markDirty();
                    isCompressing = true;

                    // change to lit
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(SingularityCompressorBlock.LIT, true), 3);
                    sync();
                }
            }
        }

        // currently compressing, tick
        else {

            // check if we're finished compressing
            if(compressionProgress >= REQUIRED_PROGRESS) {

                // double check output doesn't have anything in it
                if(output.isEmpty()) {
                    markDirty();

                    // get the resulting singularity
                    Singularity result = SingularityUtils.getCompressionResult(input.getItem());

                    // take required count from input and insert 1 into output
                    input.decrement(result.getCost());
                    output.setStack(0, new ItemStack(InfintusItems.SINGULARITIES.get(result)));

                    // reset
                    isCompressing = false;
                    compressionProgress = 0;

                    // change to non-lit
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(SingularityCompressorBlock.LIT, false), 3);
                    sync();
                }
            } else {
                compressionProgress++;
                sync();
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.put("Input", toTag(input));
        tag.put("Output", output.getTags());
        tag.putInt("Progress", compressionProgress);
        tag.putBoolean("IsCompressing", isCompressing);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        if(!tag.isEmpty()) {
            input = stackFrom(tag.getCompound("Input"));
            output.readTags(tag.getList("Output", NbtType.COMPOUND));
            this.compressionProgress = tag.getInt("Progress");
            this.isCompressing = tag.getBoolean("IsCompressing");
        }

        super.fromTag(state, tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(getCachedState(), tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }

    @Override
    public void onInventoryChanged(Inventory inventory) {
        isCompressing = false;
        compressionProgress = 0;
    }

    public double getCompressionProgress() {
        return compressionProgress / (double) REQUIRED_PROGRESS;
    }

    public static CompoundTag toTag(ItemStack stack) {
        CompoundTag tag = new CompoundTag();
        Identifier identifier = Registry.ITEM.getId(stack.getItem());
        tag.putString("id", identifier == null ? "minecraft:air" : identifier.toString());
        tag.putInt("Count", stack.getCount());
        if (stack.getTag() != null) {
            tag.put("tag", stack.getTag().copy());
        }

        return tag;
    }

    public static ItemStack stackFrom(CompoundTag tag) {
        Identifier id = new Identifier(tag.getString("id"));
        int count = tag.getInt("Count");
        Item item = Registry.ITEM.get(id);
        ItemStack itemStack = new ItemStack(item, count);

        if(tag.contains("tag")) {
            itemStack.setTag((CompoundTag) tag.get("tag"));
        }

        return itemStack;
    }
}
