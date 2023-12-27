package io.github.mortuusars.sootychimneys.integration.create;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.config.Config;
import net.minecraft.core.particles.ParticleOptions;
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
                && chimneyBlock.shouldEmitSmoke(context.state, context.world, context.localPos)
                && level.getRandom().nextDouble() < Config.SMOKE_STRENGTH.get()) {
            ParticleOptions particle = chimneyBlock.getParticle(context.state, context.contraption.getContraptionWorld(), context.localPos);
            chimneyBlock.emitParticle(level, context.position.x, context.position.y, context.position.z, particle);
        }
    }
}