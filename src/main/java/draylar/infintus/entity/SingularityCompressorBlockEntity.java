package draylar.infintus.entity;

import draylar.infintus.block.SingularityCompressorBlock;
import draylar.infintus.config.Singularity;
import draylar.infintus.registry.Entities;
import draylar.infintus.registry.Items;
import draylar.infintus.util.SingularityUtils;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import spinnery.common.BaseInventory;
import spinnery.util.InventoryUtilities;

public class SingularityCompressorBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, InventoryListener {

    public BaseInventory baseInventory = new BaseInventory(2);
    private boolean isCompressing;
    private int compressionProgress;
    private static final int REQUIRED_PROGRESS = 20 * 10;

    public SingularityCompressorBlockEntity() {
        super(Entities.SINGULARITY_COMPRESSOR);
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
            if(SingularityUtils.isValidMaterial(baseInventory.getInvStack(0).getItem())) {

                // get the resulting singularity
                Singularity result = SingularityUtils.getCompressionResult(baseInventory.getInvStack(0).getItem());

                // check if we have the required count & there is nothing in the output
                if(baseInventory.getInvStack(0).getCount() >= result.getCost() && baseInventory.getInvStack(1).isEmpty()) {
                    markDirty();
                    isCompressing = true;

                    // change to lit
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(SingularityCompressorBlock.LIT, true), 3);
                }
            }
        }

        // currently compressing, tick
        else {

            // check if we're finished compressing
            if(compressionProgress >= REQUIRED_PROGRESS) {

                // double check output doesn't have anything in it
                if(baseInventory.getInvStack(1).isEmpty()) {
                    markDirty();

                    // get the resulting singularity
                    Singularity result = SingularityUtils.getCompressionResult(baseInventory.getInvStack(0).getItem());

                    // take required count from input and insert 1 into output
                    baseInventory.getInvStack(0).decrement(result.getCost());
                    baseInventory.setInvStack(1, new ItemStack(Items.SINGULARITIES.get(result)));

                    // reset
                    isCompressing = false;
                    compressionProgress = 0;

                    // change to non-lit
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(SingularityCompressorBlock.LIT, false), 3);
                }
            } else {
                compressionProgress++;
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.put("Inventory", InventoryUtilities.write(baseInventory));
        tag.putInt("Progress", compressionProgress);
        tag.putBoolean("IsCompressing", isCompressing);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        if(!tag.isEmpty()) {
            InventoryUtilities.read(baseInventory, (CompoundTag) tag.get("Inventory"));
            this.compressionProgress = tag.getInt("Progress");
            this.isCompressing = tag.getBoolean("IsCompressing");
        }

        super.fromTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }

    @Override
    public void onInvChange(Inventory inventory) {
        baseInventory.markDirty();

        isCompressing = false;
        compressionProgress = 0;
    }

    public int getCompressionProgress() {
        return compressionProgress;
    }

    public int getRequiredProgress() {
        return REQUIRED_PROGRESS;
    }
}
