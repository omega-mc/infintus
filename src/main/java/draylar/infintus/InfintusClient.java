package draylar.infintus;

import draylar.infintus.client.screen.SingularityCompressorContainerScreen;
import draylar.infintus.container.SingularityCompressorContainer;
import draylar.infintus.registry.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import spinnery.client.InGameHudScreen;
import spinnery.widget.*;

public class InfintusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(
                Infintus.SINGULARITY_COMPRESSOR_CONTAINER,
                ((i, identifier, playerEntity, packetByteBuf) -> new SingularityCompressorContainerScreen(
                        new LiteralText("?"),
                        new SingularityCompressorContainer(i, playerEntity.inventory, packetByteBuf.readBlockPos()),
                        playerEntity
                ))
        );

        Items.SINGULARITIES.forEach((singularity, item) -> {
            if (singularity.doAutoHue()) {
                ColorProviderRegistry.ITEM.register((stack, layer) -> singularity.getColor(), item);
            }
        });
    }
}
