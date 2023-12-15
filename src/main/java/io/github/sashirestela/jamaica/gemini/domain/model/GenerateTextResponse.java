package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record GenerateTextResponse(
        List<TextCompletion> candidates,
        List<ContentFilter> filters,
        List<SafetyFeedback> safetyFeedback) {

    public record TextCompletion(
            String output,
            List<SafetyRating> safetyRatings,
            CitationMetadata citationMetadata) {
    }

    public record SafetyFeedback(
            SafetyRating rating,
            SafetySetting setting) {
    }
}
