package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.Arrays;
import java.util.List;

import io.github.sashirestela.jamaica.gemini.support.Common;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record MetadataFilter(
        @NonNull String key,
        @NonNull @Singular List<Condition> conditions) {

    @Builder
    public record Condition(
            @NonNull Operator operation,
            String stringValue,
            Double numericValue) {

        public Condition {
            var fieldsValues = Arrays.asList(stringValue, numericValue);
            var fieldsNames = "stringValue, numericValue";
            Common.validateExactlyOneIsSetted(fieldsValues, fieldsNames);
        }
    }

    public enum Operator {
        OPERATOR_UNSPECIFIED,
        LESS,
        LESS_EQUAL,
        EQUAL,
        GREATER_EQUAL,
        GREATER,
        NOT_EQUAL,
        INCLUDES,
        EXCLUDES;
    }
}
