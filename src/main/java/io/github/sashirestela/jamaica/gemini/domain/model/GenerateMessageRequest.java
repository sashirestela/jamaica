package io.github.sashirestela.jamaica.gemini.domain.model;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record GenerateMessageRequest(
        @NonNull MessagePrompt prompt,
        Double temperature,
        Integer candidateCount,
        Double topP,
        Integer topK) {
}
