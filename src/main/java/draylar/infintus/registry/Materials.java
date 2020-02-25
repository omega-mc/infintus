package draylar.infintus.registry;

import draylar.infintus.material.InfinityArmorMaterial;
import draylar.infintus.material.InfinityToolMaterial;
import draylar.infintus.material.UltimateArmorMaterial;
import draylar.infintus.material.UltimateToolMaterial;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;

public class Materials {

    public static final ToolMaterial ULTIMATE_TOOL = new UltimateToolMaterial();
    public static final ToolMaterial INFINITY_TOOL = new InfinityToolMaterial();

    public static final ArmorMaterial ULTIMATE_ARMOR = new UltimateArmorMaterial();
    public static final ArmorMaterial INFINITY_ARMOR = new InfinityArmorMaterial();
}
