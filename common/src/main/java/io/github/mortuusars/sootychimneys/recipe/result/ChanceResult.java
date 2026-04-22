package io.github.mortuusars.sootychimneys.recipe.result;

import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;

public record ChanceResult(ItemStackTemplate stack, float chance) {
    public static final Codec<ChanceResult> CHANCE_RESULT_ONLY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    ItemStackTemplate.CODEC.fieldOf("item").forGetter(ChanceResult::stack),
                    Codec.FLOAT
                            .optionalFieldOf("chance", 1F)
                            .validate(chance -> {
                                if (Float.isNaN(chance)) {
                                    return DataResult.error(() -> "'chance' cannot be NaN.");
                                } else if (chance <= 0.0)
                                    return DataResult.error(() -> "'chance' '{" + chance + "}' is not valid. Should be larger than 0.");
                                return DataResult.success(chance);
                            })
                            .forGetter(ChanceResult::chance))
            .apply(instance, ChanceResult::new));

    public static final Codec<ChanceResult> CODEC = Codec.either(ItemStackTemplate.CODEC, CHANCE_RESULT_ONLY_CODEC).xmap(
            itemStackOrChanceResult -> Either.unwrap(itemStackOrChanceResult.mapLeft(ChanceResult::new)),
            chanceResult -> chanceResult.chance() >= 1f ? Either.left(chanceResult.stack()) : Either.right(chanceResult)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ChanceResult> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC, ChanceResult::stack,
            ByteBufCodecs.FLOAT, ChanceResult::chance,
            ChanceResult::new
    );

    public ChanceResult(ItemStackTemplate stack, float chance) {
        this.stack = stack;
        Preconditions.checkArgument(!Float.isNaN(chance), "Chance '{}' is not valid. Should not be NaN.", chance);
        Preconditions.checkArgument(chance > 0.0, "Chance '{}' is not valid. Should be larger than 0.", chance);
        this.chance = chance;
    }

    public ChanceResult(ItemStackTemplate stack) {
        this(stack, 1f);
    }

    public ItemStack rollOutput(RandomSource rand) {
        int outputAmount = stack.count();
        for (int roll = 0; roll < stack.count(); roll++)
            if (rand.nextFloat() > chance)
                outputAmount--;
        if (outputAmount == 0)
            return ItemStack.EMPTY;
        ItemStack out = stack.create();
        out.setCount(outputAmount);
        return out;
    }
}
