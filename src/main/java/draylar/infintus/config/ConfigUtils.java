package draylar.infintus.config;

import draylar.infintus.Infintus;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigManager;
import me.sargunvohra.mcmods.autoconfig1u.serializer.ConfigSerializer;

public class ConfigUtils {

    private ConfigUtils() {
        // NO-OP
    }

    public static void serializeConfig() {
        try {
            ((ConfigManager<ModConfig>) AutoConfig.getConfigHolder(ModConfig.class)).getSerializer().serialize(Infintus.CONFIG);
        } catch (ConfigSerializer.SerializationException serializeException) {
            Infintus.LOGGER.error("Failed to serialize " + Infintus.LOGGER.getName() + "'s config!", serializeException);
        }
    }
}
