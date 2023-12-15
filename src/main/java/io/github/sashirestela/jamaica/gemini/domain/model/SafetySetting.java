package io.github.sashirestela.jamaica.gemini.domain.model;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record SafetySetting(
        @NonNull HarmCategory category,
        @NonNull HarmBlockThreshold threshold) {

    public enum HarmBlockThreshold {
        HARM_BLOCK_THRESHOLD_UNSPECIFIED,
        BLOCK_LOW_AND_ABOVE,
        BLOCK_MEDIUM_AND_ABOVE,
        BLOCK_ONLY_HIGH,
        BLOCK_NONE;
    }
}
