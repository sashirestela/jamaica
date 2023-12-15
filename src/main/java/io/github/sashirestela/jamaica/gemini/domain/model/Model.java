package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record Model(
        String name,
        String version,
        String displayName,
        String description,
        Integer inputTokenLimit,
        Integer outputTokenLimit,
        List<String> supportedGenerationMethods,
        Double temperature,
        Double topP,
        Integer topK) {
}
