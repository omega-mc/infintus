package draylar.infintus.registry;

import draylar.infintus.Infintus;
import draylar.infintus.config.Singularity;
import draylar.infintus.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class InfintusItems {

    // gear
    public static final Item INFINITY_SWORD = register("infinity_sword", new InfinitySwordItem());
    public static final Item INFINITY_PICKAXE = register("infinity_pickaxe", new InfinityPickaxeItem());
    public static final Item INFINITY_AXE = register("infinity_axe", new InfinityShovelItem());
    public static final Item INFINITY_SHOVEL = register("infinity_shovel", new InfinityAxeItem());
    public static final Item INFINITY_HOE = register("infinity_hoe", new InfinityHoeItem());
    public static final Item INFINITY_DAGGER = register("infinity_dagger", new InfinityDaggerItem());
    
    public static final Item INFINITY_HELMET = register("infinity_helmet", new InfinityHelmetItem());
    public static final Item INFINITY_CHESTPLATE = register("infinity_chestplate", new InfinityChestplateItem());
    public static final Item INFINITY_LEGGINGS = register("infinity_leggings", new InfinityLeggingsItem());
    public static final Item INFINITY_BOOTS = register("infinity_boots", new InfinityBootsItem());

    // mechanics
    public static final Item SINGULARITY_COMPRESSOR = register("singularity_compressor", new BlockItem(InfintusBlocks.SINGULARITY_COMPRESSOR, new Item.Settings().group(Infintus.MAIN_GROUP)));

    // other items
    public static final Item GARDEN_OF_EVE = register("garden_of_eve", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(100000).build()).group(Infintus.MAIN_GROUP)));
    public static final Item ULTIMATE_DINER_DELUXE = register("ultimate_diner_deluxe", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(100000).build()).group(Infintus.MAIN_GROUP)));

    // base singularities
    public static final Item INFINITY_SINGULARITY = register("infinity_singularity", new Item(new Item.Settings().group(Infintus.MAIN_GROUP)));

    // auto-genned singularities
    public static final HashMap<Singularity, SingularityItem> SINGULARITIES = new HashMap<>();


    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Infintus.MODID, name), item);
    }

    public static void init() {
        for(Singularity singularity: Infintus.CONFIG.singularities) {
            SingularityItem registered = Registry.register(Registry.ITEM,Infintus.id(singularity.getName() + "_singularity"), new SingularityItem(singularity));
            SINGULARITIES.put(singularity, registered);
        }
    }

    private InfintusItems() {
        // NO-OP
    }
}
