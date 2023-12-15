package io.github.sashirestela.jamaica.gemini.domain.model;

public record ContentFilter(
        BlockedReason reason,
        String message) {

    public enum BlockedReason {
        BLOCKED_REASON_UNSPECIFIED,
        SAFETY,
        OTHER;
    }
}
