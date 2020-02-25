package draylar.infintus.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import draylar.infintus.Infintus;
import draylar.infintus.container.SingularityCompressorContainer;
import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.container.AbstractFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import spinnery.common.BaseContainerScreen;
import spinnery.widget.*;

public class SingularityCompressorContainerScreen extends BaseContainerScreen<SingularityCompressorContainer> {

    private SingularityCompressorContainer linkedContainer;

    public SingularityCompressorContainerScreen(Text name, SingularityCompressorContainer linkedContainer, PlayerEntity player) {
        super(name, linkedContainer, player);

        WInterface mainInterface = new WInterface(WPosition.of(WType.FREE, 0, 0, 0), WSize.of(176, 166), linkedContainer);
        mainInterface.center();
        getHolder().add(mainInterface);

        this.linkedContainer = linkedContainer;

        // player inventory
        WSlot.addArray(WPosition.of(WType.ANCHORED, 7, y * 18 + 83 + 4 + 54, 0, mainInterface), WSize.of(18, 18), mainInterface, 0,0, 9, 1);
        WSlot.addArray(WPosition.of(WType.ANCHORED, 7, y * 18 + 83, 0, mainInterface), WSize.of(18, 18), mainInterface, 9, 0, 9, 3);

        // compressor slots
        WSlot.addSingle(WPosition.of(WType.ANCHORED, mainInterface.getWidth() / 2 - 9 - 30, 33, 1, mainInterface), WSize.of(18, 18), mainInterface, 0,  1);
        WSlot.addSingle(WPosition.of(WType.ANCHORED, mainInterface.getWidth() / 2 - 9 + 30, 33, 1, mainInterface), WSize.of(18, 18), mainInterface, 1,  1);

        // max slot count
        for (WWidget widget : mainInterface.getWidgets()) {
            if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == 1 && ((WSlot) widget).getSlotNumber() == 0) {
                ((WSlot) widget).setOverrideMaximumCount(true);
                ((WSlot) widget).setMaximumCount(1000000);
            }
        }
    }

    @Override
    protected void drawBackground(float tick, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("minecraft:textures/item/diamond.png"));
        this.blit(0, 0, 0, 0, 16, 16);
    }

    @Override
    public void render(int mouseX, int mouseY, float tick) {
        super.render(mouseX, mouseY, tick);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("minecraft:textures/item/diamond.png"));
        this.blit(0, 0, 0, 0, 16, 16);
    }

    @Override
    protected void drawForeground(int i, int j) {
        super.drawForeground(i, j);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("minecraft:textures/item/diamond.png"));
        this.blit(0, 0, 0, 0, 16, 16);
    }
}