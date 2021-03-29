package draylar.infintus;

import draylar.infintus.config.ModConfig;
import draylar.infintus.registry.InfintusBlocks;
import draylar.infintus.registry.InfintusEntities;
import draylar.infintus.registry.InfintusItems;
import draylar.infintus.ui.SingularityCompressorScreenHandler;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Infintus implements ModInitializer {

	public static final String MODID = "infintus";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ModConfig CONFIG = OmegaConfig.register(ModConfig.class);
	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(net.minecraft.item.Items.DIAMOND));
	public static final Identifier SINGULARITY_COMPRESSOR_ID = id("singularity_compressor_container");
	public static final ScreenHandlerType<SingularityCompressorScreenHandler> SINGULARITY_COMPRESSOR_HANDLER
			= ScreenHandlerRegistry.registerExtended(SINGULARITY_COMPRESSOR_ID, SingularityCompressorScreenHandler::new);

	@Override
	public void onInitialize() {
		InfintusBlocks.init();
		InfintusItems.init();
		InfintusEntities.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
