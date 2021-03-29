package draylar.infintus.recipe;

import com.google.gson.JsonObject;
import draylar.infintus.registry.InfintusRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

import java.util.Map;

public class InfinityShapedRecipe extends ShapedRecipe {

    public InfinityShapedRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, ItemStack output) {
        super(id, group, width, height, ingredients, output);
    }

    @Override
    public RecipeType<?> getType() {
        return InfintusRecipes.INFINITY_CRAFTING_TYPE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return InfintusRecipes.INFINITY_CRAFTING_SHAPED_SERIALIZER;
    }

    public static class Serializer implements RecipeSerializer<InfinityShapedRecipe> {

        @Override
        public InfinityShapedRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            Map<String, Ingredient> map = ShapedRecipe.getComponents(JsonHelper.getObject(jsonObject, "key"));
            String[] strings = ShapedRecipe.combinePattern(ShapedRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            DefaultedList<Ingredient> defaultedList = ShapedRecipe.getIngredients(strings, map, i, j);
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new InfinityShapedRecipe(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public InfinityShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readVarInt();
            int j = packetByteBuf.readVarInt();
            String string = packetByteBuf.readString(32767);
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);

            for (int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack itemStack = packetByteBuf.readItemStack();
            return new InfinityShapedRecipe(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, InfinityShapedRecipe recipe) {
            packetByteBuf.writeVarInt(recipe.width);
            packetByteBuf.writeVarInt(recipe.height);
            packetByteBuf.writeString(recipe.group);

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(packetByteBuf);
            }

            packetByteBuf.writeItemStack(recipe.output);
        }
    }
}
