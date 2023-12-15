package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Singular;

@Builder
public record GenerationConfig(
        @Singular List<String> stopSequences,
        Integer candidateCount,
        Integer maxOutputTokens,
        Double temperature,
        Double topP,
        Integer topK) {
}
