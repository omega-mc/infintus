package draylar.infintus;

import draylar.infintus.container.SingularityCompressorContainer;
import draylar.infintus.registry.Blocks;
import draylar.infintus.registry.Items;
import draylar.infintus.registry.Entities;
import draylar.infintus.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Infintus implements ModInitializer {

	public static final String MODID = "infintus";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();
	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(net.minecraft.item.Items.DIAMOND));
	public static final Identifier SINGULARITY_COMPRESSOR_CONTAINER = id("singularity_compressor_container");
	public static final Identifier INFINITY_CRAFTING_CONTAINER = id("infinity_crafting_container");

	@Override
	public void onInitialize() {
		Blocks.init();
		Items.init();
		Entities.init();

		ContainerProviderRegistry.INSTANCE.registerFactory(
				SINGULARITY_COMPRESSOR_CONTAINER,
				(syncID, identifier, playerEntity, packetByteBuf) -> new SingularityCompressorContainer(syncID, playerEntity.inventory, packetByteBuf.readBlockPos())
		);
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
