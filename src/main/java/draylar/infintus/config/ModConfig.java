package draylar.infintus.config;

import draylar.infintus.Infintus;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

import java.util.Arrays;
import java.util.List;

@Config(name = Infintus.MODID)
public class ModConfig implements ConfigData {

    public List<Singularity> singularities = Arrays.asList(
            new Singularity.Builder()
                    .withName("iron")
                    .fromMaterial("minecraft:iron_ingot")
                    .costs(10000)
                    .withColor(0xE5E5E5)
                    .build(),
            new Singularity.Builder()
                    .withName("gold")
                    .fromMaterial("minecraft:gold_ingot")
                    .costs(10000)
                    .withColor(0xF4C628)
                    .build(),
            new Singularity.Builder()
                    .withName("redstone")
                    .fromMaterial("minecraft:redstone")
                    .costs(10000)
                    .withColor(0xed1f26)
                    .build(),
            new Singularity.Builder()
                    .withName("lapis")
                    .fromMaterial("minecraft:lapis_lazuli")
                    .costs(10000)
                    .withColor(0x283fa6)
                    .build(),
            new Singularity.Builder()
                    .withName("diamond")
                    .fromMaterial("minecraft:diamond")
                    .costs(10000)
                    .withColor(0x28F4EE)
                    .build(),
            new Singularity.Builder()
                    .withName("emerald")
                    .fromMaterial("minecraft:emerald")
                    .costs(10000)
                    .withColor(0x5ef25e)
                    .build(),
            new Singularity.Builder()
                    .withName("prismarine")
                    .fromMaterial("minecraft:prismarine_shard")
                    .costs(10000)
                    .withColor(0x37C1A4)
                    .build(),
            new Singularity.Builder()
                    .withName("nether_star")
                    .fromMaterial("minecraft:nether_star")
                    .costs(100)
                    .withColor(0xE3E3E3)
                    .build(),

            new Singularity.Builder()
                    .withName("ender")
                    .fromMaterial("minecraft:ender_pearl")
                    .costs(10000)
                    .withColor(0x602496)
                    .build(),
            new Singularity.Builder()
                    .withName("bacon")
                    .fromMaterial("minecraft:cooked_porkchop")
                    .costs(10000)
                    .withColor(0xfaca8e)
                    .build(),
            new Singularity.Builder()
                    .withName("beef")
                    .fromMaterial("minecraft:cooked_beef")
                    .costs(10000)
                    .withColor(0x78401f)
                    .build(),
            new Singularity.Builder()
                    .withName("bread")
                    .fromMaterial("minecraft:bread")
                    .costs(10000)
                    .withColor(0xffeca1)
                    .build(),
            new Singularity.Builder()
                    .withName("cake")
                    .fromMaterial("minecraft:cake")
                    .costs(10000)
                    .withColor(0xffeca1)
                    .build()
    );
}
