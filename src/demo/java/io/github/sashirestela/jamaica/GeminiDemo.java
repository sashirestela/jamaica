package io.github.sashirestela.jamaica;

import io.github.sashirestela.jamaica.gemini.domain.PageQuery;
import io.github.sashirestela.jamaica.gemini.domain.model.Content;
import io.github.sashirestela.jamaica.gemini.domain.model.CountMessageTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.Content.Part;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerRequest.AnswerStyle;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerRequest.GroundingPassage;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerRequest.GroundingPassages;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTextTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.CountTokensRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateAnswerRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateContentRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateMessageRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerateTextRequest;
import io.github.sashirestela.jamaica.gemini.domain.model.GenerationConfig;
import io.github.sashirestela.jamaica.gemini.domain.model.Message;
import io.github.sashirestela.jamaica.gemini.domain.model.MessagePrompt;
import io.github.sashirestela.jamaica.gemini.domain.model.TextPrompt;

public class GeminiDemo {

    public static void main(String[] args) {
        var gemini = JamaicaGemini.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .authToken(System.getenv("GEMINI_AUTH_TOKEN"))
                .build();

        var modelId1 = "models/chat-bison-001";
        var modelId2 = "models/text-bison-001";
        var modelId3 = "models/gemini-pro";
        var modelId4 = "models/aqa";
        var pageSize = 4;
        var inputText = "";
        var outputText = "";

        String pageToken = null;
        do {
            var modelList = gemini.models().getList(new PageQuery(pageSize, pageToken)).join();
            modelList.models().forEach(System.out::println);
            pageToken = modelList.nextPageToken();
            System.out.println();
        } while (pageToken != null);

        var model = gemini.models().getOne(modelId2).join();
        System.out.println(model);
        System.out.println();

        inputText = System.console().readLine("Make a question to the AI (" + modelId1 + ") : ");
        var generateMessageRequest = GenerateMessageRequest.builder()
                .prompt(MessagePrompt.builder()
                        .message(Message.builder()
                                .content(inputText)
                                .build())
                        .build())
                .temperature(0.1)
                .build();
        var generateMessageResponse = gemini.models().generateMessage(generateMessageRequest, modelId1).join();
        outputText = generateMessageResponse.candidates().get(0).content();
        System.out.println(outputText);
        System.out.println();

        var countMessageTokensRequest = CountMessageTokensRequest.builder()
                .prompt(MessagePrompt.builder()
                        .message(Message.builder()
                                .content(outputText)
                                .build())
                        .build())
                .build();
        var countMessageTokensResponse = gemini.models().countMessageTokens(countMessageTokensRequest, modelId1).join();
        System.out.println(countMessageTokensResponse.tokenCount());
        System.out.println();

        inputText = System.console().readLine("Make a question to the AI (" + modelId2 + ") : ");
        var generateTextRequest = GenerateTextRequest.builder()
                .prompt(new TextPrompt(inputText))
                .temperature(0.1)
                .maxOutputTokens(200)
                .build();
        var generateTextResponse = gemini.models().generateText(generateTextRequest, modelId2).join();
        outputText = generateTextResponse.candidates().get(0).output();
        System.out.println(outputText);
        System.out.println();

        var countTextTokensRequest = CountTextTokensRequest.builder()
                .prompt(new TextPrompt(outputText))
                .build();
        var countTextTokensResponse = gemini.models().countTextTokens(countTextTokensRequest, modelId2).join();
        System.out.println(countTextTokensResponse.tokenCount());
        System.out.println();

        inputText = System.console().readLine("Make a question to the AI (" + modelId3 + ") : ");
        var generateContentRequest = GenerateContentRequest.builder()
                .content(Content.builder()
                        .part(Part.builder()
                                .text(inputText)
                                .build())
                        .build())
                .generationConfig(GenerationConfig.builder()
                        .temperature(0.1)
                        .maxOutputTokens(200)
                        .build())
                .build();
        var generateContentResponse = gemini.models().generateContent(generateContentRequest, modelId3).join();
        outputText = generateContentResponse.candidates().get(0).content().parts().get(0).text();
        System.out.println(outputText);
        System.out.println();

        var countTokensRequest = CountTokensRequest.builder()
                .content(Content.builder()
                        .part(Part.builder()
                                .text(outputText)
                                .build())
                        .build())
                .build();
        var countTokensResponse = gemini.models().countTokens(countTokensRequest, modelId3).join();
        System.out.println(countTokensResponse.totalTokens());
        System.out.println();

        inputText = System.console().readLine("Make a question to the AI (" + modelId4 + ") : ");
        var generateAnswerRequest = GenerateAnswerRequest.builder()
                .content(Content.builder()
                        .part(Part.builder()
                                .text(inputText)
                                .build())
                        .build())
                .answerStyle(AnswerStyle.VERBOSE)
                .inlinePassages(GroundingPassages.builder()
                        .passage(GroundingPassage.builder()
                                .id("abc")
                                .content(Content.builder()
                                        .part(Part.builder()
                                                .text(inputText)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .temperature(0.2)
                .build();
        var generateAnswerResponse = gemini.models().generateAnswer(generateAnswerRequest, modelId4).join();
        outputText = generateAnswerResponse.answer().content().parts().get(0).text();
        System.out.println(outputText);
        System.out.println();
    }
}
