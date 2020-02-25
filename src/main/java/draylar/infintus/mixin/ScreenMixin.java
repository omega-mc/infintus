package draylar.infintus.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import draylar.infintus.item.SingularityItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin extends AbstractParentElement implements Drawable {

    @Shadow public abstract List<String> getTooltipFromItem(ItemStack itemStack);
    @Shadow protected TextRenderer font;
    @Shadow public int width;
    @Shadow public int height;

    @Shadow protected ItemRenderer itemRenderer;

    @Inject(at = @At("HEAD"), method = "renderTooltip(Lnet/minecraft/item/ItemStack;II)V", cancellable = true)
    private void renderTooltip(ItemStack stack, int x, int y, CallbackInfo ci) {

        if(stack.getItem() instanceof SingularityItem) {
            render(stack, x, y);
            ci.cancel();
        }
    }
    
    @Unique
    private void render(ItemStack stack, int x, int y) {
        List<String> lines = getTooltipFromItem(stack);
        SingularityItem stackItem = (SingularityItem) stack.getItem(); 
        
        if (!lines.isEmpty()) {
            RenderSystem.disableRescaleNormal();
            RenderSystem.disableDepthTest();
            
            int longestWidth = 0;

            for (String line : lines) {
                int lineWidth = this.font.getStringWidth(line);

                if (lineWidth > longestWidth) {
                    longestWidth = lineWidth;
                }
            }

            int tooltipX = x + 12;
            int tooltipY = y - 12;
            int linesHeight = 8;
            
            if (lines.size() > 1) {
                linesHeight += 2 + (lines.size() - 1) * 10;
            }

            if (tooltipX + longestWidth > this.width) {
                tooltipX -= 28 + longestWidth;
            }

            if (tooltipY + linesHeight + 6 > this.height) {
                tooltipY = this.height - linesHeight - 6;
            }

            this.setBlitOffset(300);
            this.itemRenderer.zOffset = 300.0F;


            // 5x fill gradients: center box, 4x 1px wide edges

            int extraLength = 12;

            // translucent blue core background
            int o = -267386864;
            this.fillGradient(tooltipX - 3, tooltipY - 4, tooltipX + longestWidth + 3 + extraLength, tooltipY - 3, o, o); // top
            this.fillGradient(tooltipX - 3, tooltipY + linesHeight + 3, tooltipX + longestWidth + 3 + extraLength, tooltipY + linesHeight + 4, o, o); // bottom
            this.fillGradient(tooltipX - 3, tooltipY - 3, tooltipX + longestWidth + 3 + extraLength, tooltipY + linesHeight + 3, o, o); // middle
            this.fillGradient(tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + linesHeight + 3, o, o); // left
            this.fillGradient(tooltipX + longestWidth + 3 + extraLength + 1, tooltipY - 3, tooltipX + longestWidth + 4, tooltipY + linesHeight + 3, o, o); // right

            // purple edge
            int p = 1347420415;
            int q = 1344798847;
            this.fillGradient(tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + linesHeight + 3 - 1, p, q); // left
            this.fillGradient(tooltipX + longestWidth + 2  + extraLength, tooltipY - 3 + 1, tooltipX + longestWidth + 3  + extraLength, tooltipY + linesHeight + 3 - 1, p, q); // right
            this.fillGradient(tooltipX - 3, tooltipY - 3, tooltipX + longestWidth + 3 + extraLength, tooltipY - 3 + 1, p, p); // bottom
            this.fillGradient(tooltipX - 3, tooltipY + linesHeight + 2, tooltipX + longestWidth + 3 + extraLength, tooltipY + linesHeight + 3, q, q); // top

            MatrixStack matrixStack = new MatrixStack();
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
            matrixStack.translate(0.0D, 0.0D, this.itemRenderer.zOffset);
            Matrix4f matrix4f = matrixStack.peek().getModel();

            // render item after text

            MinecraftClient.getInstance().getItemRenderer().renderGuiItemIcon(new ItemStack(Registry.ITEM.get(new Identifier(stackItem.getSingularity().getSourceID()))), tooltipX + longestWidth - 1, tooltipY - 4);


            for(int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                String line = lines.get(lineIndex);

                if (line != null) {
                    this.font.draw(line, (float) tooltipX, (float) tooltipY, -1, true, matrix4f, immediate, false, 0, 15728880);
                }

                if (lineIndex == 0) {
                    tooltipY += 2;
                }

                tooltipY += 10;
            }



            immediate.draw();
            this.setBlitOffset(0);
            this.itemRenderer.zOffset = 0.0F;

            RenderSystem.enableDepthTest();
            RenderSystem.enableRescaleNormal();
        }
    }
}
