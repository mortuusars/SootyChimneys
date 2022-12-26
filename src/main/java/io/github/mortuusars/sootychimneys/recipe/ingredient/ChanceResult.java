package io.github.mortuusars.sootychimneys.recipe.ingredient;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.Random;

/**
 * Credits to the Create team (and Farmer's Delight) for the implementation of results with chances!
 */
@SuppressWarnings({"deprecation", "ClassCanBeRecord", "Convert2MethodRef"})
public class ChanceResult {

    public static final Codec<ChanceResult> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    // Not using ItemStack.CODEC here. This way json is more intuitive, without one more object.
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(chanceResult -> chanceResult.stack.getItem()),
                    Codec.INT.optionalFieldOf("count", 1).forGetter(chanceResult -> chanceResult.stack.getCount()),
                    CompoundTag.CODEC.optionalFieldOf("tag").forGetter(chanceResult -> Optional.ofNullable(chanceResult.stack.getTag())),
                    Codec.FLOAT.optionalFieldOf("chance", 1F).forGetter(ChanceResult::getChance))
            .apply(instance, (item, count, compoundTag, chance) -> {
                ItemStack stack = new ItemStack(item, count);
                compoundTag.ifPresent(tag -> stack.setTag(tag));
                return new ChanceResult(stack, chance);
            }));

    public static final ChanceResult EMPTY = new ChanceResult(ItemStack.EMPTY, 1);

    private final ItemStack stack;
    private final float chance;

    public ChanceResult(ItemStack stack, float chance) {
        this.stack = stack;

        if (chance == 0 || Float.isNaN(chance))
            throw new IllegalArgumentException("Chance cannot be 0 or NaN.\n" + this.toJson().toString());

        this.chance = chance;
    }

    public ItemStack getStack() {
        return stack;
    }

    public float getChance() {
        return chance;
    }

    public ItemStack rollOutput(Random rand) {
        int outputAmount = stack.getCount();
        for (int roll = 0; roll < stack.getCount(); roll++)
            if (rand.nextFloat() > chance)
                outputAmount--;
        if (outputAmount == 0)
            return ItemStack.EMPTY;
        ItemStack out = stack.copy();
        out.setCount(outputAmount);
        return out;
    }

    public JsonElement toJson() {
        return CODEC.encodeStart(JsonOps.INSTANCE, this).getOrThrow(false,
                (String error) -> { throw new IllegalStateException(error); });
    }

    public static ChanceResult fromJson(JsonElement jsonElement) {
        if (!jsonElement.isJsonObject())
            throw new JsonSyntaxException("Must be a json object");

        return CODEC.decode(JsonOps.INSTANCE, jsonElement).getOrThrow(false,
                (String error) -> { throw new IllegalStateException(error); }).getFirst();
    }

    public void toBuffer(FriendlyByteBuf buf) {
        buf.writeItem(getStack());
        buf.writeFloat(getChance());
    }

    public static ChanceResult fromBuffer(FriendlyByteBuf buf) {
        return new ChanceResult(buf.readItem(), buf.readFloat());
    }
}
