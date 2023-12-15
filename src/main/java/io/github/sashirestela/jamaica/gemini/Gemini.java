package io.github.sashirestela.jamaica.gemini;

import java.util.concurrent.CompletableFuture;

import io.github.sashirestela.cleverclient.annotation.Body;
import io.github.sashirestela.cleverclient.annotation.GET;
import io.github.sashirestela.cleverclient.annotation.POST;
import io.github.sashirestela.cleverclient.annotation.Path;
import io.github.sashirestela.cleverclient.annotation.Query;
import io.github.sashirestela.cleverclient.annotation.Resource;
import io.github.sashirestela.jamaica.gemini.domain.PageQuery;
import io.github.sashirestela.jamaica.gemini.domain.model.CountMessageTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.CountMessageTokensResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTextTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTextTokensResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTokensResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateContentRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateContentResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateMessageRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateMessageResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateTextRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateTextResponse;
import io.github.sashirestela.jamaica.gemini.domain.model.Model;
import io.github.sashirestela.jamaica.gemini.domain.model.ModelList;

public interface Gemini {

    @Resource("/v1beta")
    interface Models {

        @GET("/models")
        CompletableFuture<ModelList> getList(@Query PageQuery page);

        @GET("/{modelId}")
        CompletableFuture<Model> getOne(@Path("modelId") String modelId);

        @POST("/{modelId}:generateMessage")
        CompletableFuture<GenerateMessageResponse> generateMessage(@Body GenerateMessageRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:countMessageTokens")
        CompletableFuture<CountMessageTokensResponse> countMessageTokens(@Body CountMessageTokensRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:generateText")
        CompletableFuture<GenerateTextResponse> generateText(@Body GenerateTextRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:countTextTokens")
        CompletableFuture<CountTextTokensResponse> countTextTokens(@Body CountTextTokensRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:generateContent")
        CompletableFuture<GenerateContentResponse> generateContent(@Body GenerateContentRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:countTokens")
        CompletableFuture<CountTokensResponse> countTokens(@Body CountTokensRequest request,
                @Path("modelId") String modelId);

        @POST("/{modelId}:generateAnswer")
        CompletableFuture<GenerateAnswerResponse> generateAnswer(@Body GenerateAnswerRequest request,
                @Path("modelId") String modelId);

    }
}
