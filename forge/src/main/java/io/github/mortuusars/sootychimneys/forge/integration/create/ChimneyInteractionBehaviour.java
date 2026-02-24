package io.github.mortuusars.sootychimneys.forge.integration.create;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.ContraptionWorld;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class ChimneyInteractionBehaviour extends MovingInteractionBehaviour {
    @Override
    public boolean handlePlayerInteraction(Player player, InteractionHand activeHand,
                                           BlockPos localPos, AbstractContraptionEntity contraptionEntity) {

        Contraption contraption = contraptionEntity.getContraption();
        ContraptionWorld contraptionLevel = contraption.getContraptionWorld();
        BlockState state = contraptionLevel.getBlockState(localPos);
        if (!(state.getBlock() instanceof ChimneyBlock chimney)) {
            return false;
        }
        @Nullable MutablePair<StructureTemplate.StructureBlockInfo, MovementContext> actor = contraption.getActorAt(localPos);

        boolean newBlockedValue = !state.getValue(ChimneyBlock.BLOCKED);
        BlockState newState = contraptionLevel.getBlockState(localPos)
                .setValue(ChimneyBlock.BLOCKED, newBlockedValue);
        StructureTemplate.StructureBlockInfo structureBlockInfo = new StructureTemplate.StructureBlockInfo(localPos, newState, null);
        if (actor == null) {
            return false;
        }

        actor.setLeft(structureBlockInfo);
        MovementContext context = actor.getRight();
        context.state = newState;

        if (!contraptionLevel.isClientSide) {
            contraptionEntity.setBlock(localPos, structureBlockInfo);

            Vector3f particleOrigin = chimney.getType().smokeProperties().getParticleOrigin();
            RandomSource random = player.level().getRandom();
            Vec3 pos = context.position;

            player.level().playSound(null, pos.x, pos.y, pos.z, newBlockedValue ? SoundEvents.LANTERN_FALL : SoundEvents.LANTERN_HIT, SoundSource.BLOCKS,
                    0.8f, 0.85f + contraptionLevel.random.nextFloat() * 0.05f);
            for (int i = 0; i < random.nextInt(5); i++) {
                ((ServerLevel) player.level()).sendParticles(ParticleTypes.SMOKE,
                        pos.x() + particleOrigin.x(), pos.y() + particleOrigin.y() - 0.1, pos.z() + particleOrigin.z(),
                        1, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, random.nextGaussian() * 0.1d, 0);
            }
        } else {
            String messageTranslationKey = "message.sootychimneys." + (newBlockedValue ? "blocked" : "open");
            player.displayClientMessage(Component.translatable(messageTranslationKey), true);
        }

        return true;
    }
}