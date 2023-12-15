package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record Tool(
        @NonNull @Singular List<FunctionDeclaration> functionDeclarations) {

    @Builder
    public record FunctionDeclaration(
            @NonNull String name,
            @NonNull String description,
            Schema parameters) {
    }

    @Builder
    public record Schema(
            @NonNull Type type,
            String format,
            String description,
            Boolean nullable,
            @Singular Map<String, Schema> properties,
            List<String> required) {
    }

    public enum Type {
        @JsonProperty("string")
        STRING,
        @JsonProperty("number")
        NUMBER,
        @JsonProperty("integer")
        INTEGER,
        @JsonProperty("boolean")
        BOOLEAN,
        @JsonProperty("array")
        ARRAY,
        @JsonProperty("object")
        OBJECT;
    }
}
