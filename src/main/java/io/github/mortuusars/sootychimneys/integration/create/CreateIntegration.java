package io.github.mortuusars.sootychimneys.integration.create;

import com.simibubi.create.AllMovementBehaviours;
import io.github.mortuusars.sootychimneys.setup.ModBlocks;

public class CreateIntegration {

    public static void registerMovingBehaviors() {
        ChimneyMovementBehaviour chimneyMovementBehaviour = new ChimneyMovementBehaviour();
//        ModBlocks.CHIMNEYS.forEach(chimneyObj -> AllMovementBehaviours.registerBehaviour(chimneyObj.get(), chimneyMovementBehaviour));
    }
}
