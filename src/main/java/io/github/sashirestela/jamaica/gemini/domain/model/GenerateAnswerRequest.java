package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.Arrays;
import java.util.List;

import io.github.sashirestela.jamaica.gemini.support.Common;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record GenerateAnswerRequest(
        @NonNull @Singular List<Content> contents,
        @NonNull AnswerStyle answerStyle,
        @Singular List<SafetySetting> safetySettings,
        GroundingPassages inlinePassages,
        SemanticRetrieverConfig semanticRetriever,
        Double temperature) {

    public GenerateAnswerRequest {
        var fieldsValues = Arrays.asList(inlinePassages, semanticRetriever);
        var fieldsNames = "inlinePassages, semanticRetriever";
        Common.validateOnlyOneIsSetted(fieldsValues, fieldsNames);
    }

    @Builder
    public record GroundingPassages(
            @Singular List<GroundingPassage> passages) {
    }

    @Builder
    public record GroundingPassage(
            String id,
            Content content) {
    }

    @Builder
    public record SemanticRetrieverConfig(
            @NonNull String source,
            @NonNull Content query,
            @Singular List<MetadataFilter> metadataFilters,
            Integer maxChunksCount,
            Double minimumRelevanceScore) {
    }

    public enum AnswerStyle {
        ANSWER_STYLE_UNSPECIFIED,
        ABSTRACTIVE,
        EXTRACTIVE,
        VERBOSE;
    }
}
