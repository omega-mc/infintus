package draylar.infintus.registry;

import draylar.infintus.Infintus;
import draylar.infintus.recipe.InfinityShapedRecipe;
import draylar.infintus.recipe.InfinityShapelessRecipe;
import net.minecraft.recipe.*;
import net.minecraft.util.registry.Registry;

public class InfintusRecipes {

    public static final RecipeSerializer<InfinityShapelessRecipe> INFINITY_CRAFTING_SHAPELESS_SERIALIZER = register("infinity_crafting_shapeless", new InfinityShapelessRecipe.Serializer());
    public static final RecipeSerializer<InfinityShapedRecipe> INFINITY_CRAFTING_SHAPED_SERIALIZER = register("infinity_crafting_shaped", new InfinityShapedRecipe.Serializer());
    public static final RecipeType<CraftingRecipe> INFINITY_CRAFTING_TYPE = register("infinity_crafting");

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return  Registry.register(Registry.RECIPE_SERIALIZER, Infintus.id(id), serializer);
    }

    public static <T extends Recipe<?>> RecipeType<T> register(String id) {
        return Registry.register(Registry.RECIPE_TYPE, Infintus.id(id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }

    public static void init() {

    }

    private InfintusRecipes() {
        // NO-OP
    }
}
