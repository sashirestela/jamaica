package io.github.sashirestela.jamaica.gemini.domain.model;

public record SafetyRating(
        HarmCategory category,
        HarmProbability probability,
        Boolean blocked) {

    public enum HarmProbability {
        HARM_PROBABILITY_UNSPECIFIED,
        NEGLIGIBLE,
        LOW,
        MEDIUM,
        HIGH;
    }
}
