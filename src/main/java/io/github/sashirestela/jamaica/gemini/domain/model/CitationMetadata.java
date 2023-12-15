package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record CitationMetadata(
        List<CitationSource> citationSources) {

    public record CitationSource(
            Integer startIndex,
            Integer endIndex,
            String uri,
            String license) {
    }
}
