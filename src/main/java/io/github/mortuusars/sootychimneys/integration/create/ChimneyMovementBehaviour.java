package io.github.mortuusars.sootychimneys.integration.create;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.setup.ModTags;
import net.minecraft.world.level.Level;

/**
 * Used to define how blocks should behave on a moving contraption.
 */
public class ChimneyMovementBehaviour implements MovementBehaviour {
    @Override
    public void tick(MovementContext context) {
        Level level = context.world;

        if (level != null && level.isClientSide && context.position != null
                && context.state.getBlock() instanceof ChimneyBlock chimneyBlock
                && chimneyBlock.shouldEmitSmoke(context.state, context.contraption.getContraptionWorld(), context.localPos)
                && level.getRandom().nextDouble() < CommonConfig.SMOKE_STRENGTH.get()) {
            chimneyBlock.emitSmokeParticles(level, context.state, context.position.x - 0.5, context.position.y - 0.5, context.position.z - 0.5,
                    level.getBlockState(context.localPos.below()).is(ModTags.Blocks.CHIMNEYS));
        }
    }
}