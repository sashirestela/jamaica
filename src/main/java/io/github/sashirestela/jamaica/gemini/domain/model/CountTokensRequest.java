package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record CountTokensRequest(
        @NonNull @Singular List<Content> contents) {
}
