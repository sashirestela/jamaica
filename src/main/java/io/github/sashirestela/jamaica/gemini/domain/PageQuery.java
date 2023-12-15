package io.github.sashirestela.jamaica.gemini.domain;

public record PageQuery(
        Integer pageSize,
        String pageToken) {
}
