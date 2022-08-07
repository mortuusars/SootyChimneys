package io.github.mortuusars.sootychimneys.compat.create;

import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.components.actors.CampfireMovementBehaviour;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;

public class CreateCompat {

    public static void registerMovingBehaviors() {
        ChimneyMovementBehaviour chimneyMovementBehaviour = new ChimneyMovementBehaviour();
        ModBlocks.CHIMNEYS.forEach(chimneyObj -> AllMovementBehaviours.registerBehaviour(chimneyObj.get().delegate, chimneyMovementBehaviour));
    }
}
