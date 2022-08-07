package io.github.mortuusars.sootychimneys.compat.create;

import com.simibubi.create.content.contraptions.components.structureMovement.MovementBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.MovementContext;
import io.github.mortuusars.sootychimneys.blocks.ChimneyBlock;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import net.minecraft.core.BlockPos;

/**
 * Used to define how blocks should behave on a moving contraption.
 */
public class ChimneyMovementBehaviour implements MovementBehaviour {
    @Override
    public boolean renderAsNormalTileEntity() {
        return true;
    }

    @Override
    public void tick(MovementContext context) {
        if (context.world == null || !context.world.isClientSide || context.position == null || !context.state.getValue(ChimneyBlock.LIT))
            return;

        if (context.state.getBlock() instanceof ChimneyBlock chimneyBlock &&
                context.world.getRandom().nextDouble(0.0001d, 1.0d) < CommonConfig.SMOKE_STRENGTH.get()) {
            chimneyBlock.emitParticles(context.world, new BlockPos(context.position.x, context.position.y, context.position.z), context.state);
        }
    }
}