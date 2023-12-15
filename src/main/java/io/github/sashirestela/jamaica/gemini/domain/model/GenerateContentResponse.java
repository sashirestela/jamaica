package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record GenerateContentResponse(
        List<Candidate> candidates,
        PromptFeedback promptFeedback) {

    public record PromptFeedback(
            BlockReason blockReason,
            List<SafetyRating> safetyRatings) {
    }

    public enum BlockReason {
        BLOCK_REASON_UNSPECIFIED,
        SAFETY,
        OTHER
    }
}
