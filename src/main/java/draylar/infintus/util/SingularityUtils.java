package draylar.infintus.util;

import draylar.infintus.Infintus;
import draylar.infintus.config.Singularity;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SingularityUtils {

    public static boolean isValidMaterial(Item item) {
        return Infintus.CONFIG.singularities.stream().anyMatch(singularity -> singularity.getSourceID().equals(Registry.ITEM.getId(item).toString()));
    }

    public static Singularity getCompressionResult(Item item) {
        for (Singularity singularity : Infintus.CONFIG.singularities) {
            if (singularity.getSourceID().equals(Registry.ITEM.getId(item).toString())) {
                return singularity;
            }
        }

        return new Singularity.Builder().build();
    }
}
