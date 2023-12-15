package io.github.sashirestela.jamaica.gemini.domain.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.sashirestela.jamaica.gemini.support.Common;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record Content(
        @NonNull @Singular List<Part> parts,
        Role role) {

    @Builder
    public record Part(
            String text,
            Blob inlineData,
            FunctionCall functionCall,
            FunctionResponse functionResponse) {

        public Part {
            var fieldsValues = Arrays.asList(text, inlineData, functionCall, functionResponse);
            var fieldsNames = "text, inlineData, functionCall, functionResponse";
            Common.validateExactlyOneIsSetted(fieldsValues, fieldsNames);
        }
    }

    @Builder
    public record Blob(
            @NonNull MimeType mimeType,
            @NonNull String data) {
    }

    @Builder
    public record FunctionCall(
            @NonNull String name,
            Object args) {
    }

    @Builder
    public record FunctionResponse(
            @NonNull String name,
            @NonNull Object response) {
    }

    public enum Role {
        @JsonProperty("user")
        USER,
        @JsonProperty("model")
        MODEL;
    }

    public enum MimeType {
        @JsonProperty("image/png")
        PNG,
        @JsonProperty("image/jpeg")
        JPEG,
        @JsonProperty("image/heic")
        HEIC,
        @JsonProperty("image/webp")
        WEBP;
    }
}
