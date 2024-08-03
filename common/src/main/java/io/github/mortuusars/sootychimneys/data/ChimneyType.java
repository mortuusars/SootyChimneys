package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.core.ChimneyShape;
import io.github.mortuusars.sootychimneys.core.smoke.SmokeProperties;

public record ChimneyType(SmokeProperties smokeProperties, ChimneyShape shape) { }
