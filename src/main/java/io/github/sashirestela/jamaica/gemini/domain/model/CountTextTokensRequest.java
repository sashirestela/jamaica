package io.github.sashirestela.jamaica.gemini.domain.model;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CountTextTokensRequest(
        @NonNull TextPrompt prompt) {
}
