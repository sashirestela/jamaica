package io.github.sashirestela.jamaica.gemini.domain.model;

public record GenerateAnswerResponse(
        Candidate answer,
        Double answerableProbability) {
}
