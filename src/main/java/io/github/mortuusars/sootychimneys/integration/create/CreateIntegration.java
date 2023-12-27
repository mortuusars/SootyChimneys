package io.github.mortuusars.sootychimneys.integration.create;

import com.simibubi.create.AllMovementBehaviours;
import io.github.mortuusars.sootychimneys.SootyChimneys;

public class CreateIntegration {
    public static void registerMovingBehaviors() {
        ChimneyMovementBehaviour chimneyMovementBehaviour = new ChimneyMovementBehaviour();
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            AllMovementBehaviours.registerBehaviour(chimney.getCleanBlock(), chimneyMovementBehaviour);
            AllMovementBehaviours.registerBehaviour(chimney.getDirtyBlock(), chimneyMovementBehaviour);
        }
    }
}
