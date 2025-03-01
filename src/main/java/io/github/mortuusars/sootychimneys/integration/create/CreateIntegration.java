package io.github.mortuusars.sootychimneys.integration.create;

import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import io.github.mortuusars.sootychimneys.SootyChimneys;

public class CreateIntegration {
    public static void registerMovingBehaviors() {
        ChimneyMovementBehaviour chimneyMovementBehaviour = new ChimneyMovementBehaviour();
        for (SootyChimneys.Chimney chimney : SootyChimneys.Chimney.values()) {
            MovementBehaviour.REGISTRY.register(chimney.getCleanBlock(), chimneyMovementBehaviour);
            MovementBehaviour.REGISTRY.register(chimney.getDirtyBlock(), chimneyMovementBehaviour);
        }
    }
}
