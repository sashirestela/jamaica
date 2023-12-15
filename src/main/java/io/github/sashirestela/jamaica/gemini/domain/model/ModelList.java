package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record ModelList(
        List<Model> models,
        String nextPageToken) {
}
