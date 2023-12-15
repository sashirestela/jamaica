package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record GenerateTextRequest(
        @NonNull TextPrompt prompt,
        SafetySetting safetySettings,
        @Singular List<String> stopSequences,
        Double temperature,
        Integer candidateCount,
        Integer maxOutputTokens,
        Double topP,
        Integer topK) {
}
