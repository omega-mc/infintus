package draylar.infintus;

import draylar.infintus.registry.InfintusItems;
import draylar.infintus.ui.SingularityCompressorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class InfintusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Infintus.SINGULARITY_COMPRESSOR_HANDLER, SingularityCompressorScreen::new);

        InfintusItems.SINGULARITIES.forEach((singularity, item) -> {
            if (singularity.doAutoHue()) {
                ColorProviderRegistry.ITEM.register((stack, layer) -> singularity.getColor(), item);
            }
        });
    }
}
