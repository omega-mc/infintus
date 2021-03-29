package draylar.infintus.ui.compressor;

import draylar.infintus.Infintus;
import draylar.infintus.entity.SingularityCompressorBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SingularityCompressorScreen extends HandledScreen<SingularityCompressorScreenHandler> {

    private static final Identifier BACKGROUND = Infintus.id("textures/ui/compressor_ui.png");
    private static final Identifier PROGRESS = Infintus.id("textures/ui/progress_top.png");
    private final SingularityCompressorBlockEntity be;

    public SingularityCompressorScreen(SingularityCompressorScreenHandler handler, PlayerInventory inventory, Text name) {
        super(handler, inventory, name);
        be = handler.getBe();
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND);
        drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        MinecraftClient.getInstance().getTextureManager().bindTexture(PROGRESS);
        drawTexture(matrices, width / 2 - 11, y + 36, 0, 0, (int) (be.getCompressionProgress() * 22), 16, 22, 16);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}