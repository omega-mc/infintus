package draylar.infintus.ui.forge;

import draylar.infintus.Infintus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InfinityForgeScreen extends HandledScreen<InfinityForgeScreenHandler> {

    public static final Identifier BACKGROUND_TEXTURE = Infintus.id("textures/ui/infinity_forge_ui.png");

    public InfinityForgeScreen(InfinityForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void init(MinecraftClient client, int width, int height) {
        int i = client.getWindow().calculateScaleFactor(client.options.guiScale, client.forcesUnicodeFont());
        client.getWindow().setScaleFactor(i - 1);

        Framebuffer framebuffer = client.getFramebuffer();
        framebuffer.resize(client.getWindow().getFramebufferWidth(), client.getWindow().getFramebufferHeight(), MinecraftClient.IS_SYSTEM_MAC);
        client.gameRenderer.onResized(client.getWindow().getFramebufferWidth(), client.getWindow().getFramebufferHeight());
        client.mouse.onResolutionChanged();

        super.init(client, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight());
    }

    @Override
    public void onClose() {
        int i = client.getWindow().calculateScaleFactor(client.options.guiScale, client.forcesUnicodeFont());
        client.getWindow().setScaleFactor(i);
        super.onClose();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        drawTexture(matrices, x, y - 50, 0, 0, 237, 278, 384, 384);
    }
}
