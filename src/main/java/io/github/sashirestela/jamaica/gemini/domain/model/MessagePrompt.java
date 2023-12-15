package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record MessagePrompt(
        @NonNull @Singular List<Message> messages,
        @Singular List<Example> examples,
        String context) {

    @Builder
    public record Example(
            @NonNull Message input,
            @NonNull Message output) {
    }
}
