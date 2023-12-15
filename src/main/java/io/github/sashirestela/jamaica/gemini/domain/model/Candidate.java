package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record Candidate(
        Content content,
        FinishReason finishReason,
        List<SafetyRating> safetyRatings,
        CitationMetadata citationMetadata,
        Integer tokenCount,
        List<GroundingAttribution> groundingAttributions,
        Integer index) {

    public record GroundingAttribution(
            AttributionSourceId sourceId,
            Content content) {
    }

    public record AttributionSourceId(
            GroundingPassageId groundingPassage,
            SemanticRetrieverChunk semanticRetrieverChunk) {
    }

    public record GroundingPassageId(
            String passageId,
            Integer partIndex) {
    }

    public record SemanticRetrieverChunk(
            String source,
            String chunk) {
    }

    public enum FinishReason {
        FINISH_REASON_UNSPECIFIED,
        STOP,
        MAX_TOKENS,
        SAFETY,
        RECITATION,
        OTHER;
    }
}
