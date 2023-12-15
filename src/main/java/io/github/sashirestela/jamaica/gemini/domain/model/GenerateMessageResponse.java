package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

public record GenerateMessageResponse(
        List<Message> candidates,
        List<Message> messages,
        List<ContentFilter> filters) {
}
