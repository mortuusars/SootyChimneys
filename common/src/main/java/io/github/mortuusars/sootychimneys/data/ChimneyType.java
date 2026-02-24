package io.github.mortuusars.sootychimneys.data;

import io.github.mortuusars.sootychimneys.data.smoke.SmokeProperties;

public record ChimneyType(SmokeProperties smokeProperties, ChimneyShape shape) { }
