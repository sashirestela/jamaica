package io.github.sashirestela.jamaica;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.github.sashirestela.cleverclient.CleverClient;
import io.github.sashirestela.jamaica.gemini.Gemini;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JamaicaGemini {

    private static final String GEMINI_URL_BASE = "https://generativelanguage.googleapis.com";
    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_PREFIX = "Bearer ";
    private static final String GEMINI_HEADER = "x-goog-api-key";

    private String authToken;
    private String apiKey;
    private String urlBase;
    private HttpClient httpClient;
    private CleverClient cleverClient;

    @Getter(AccessLevel.NONE)
    private Gemini.Models modelService;

    @Builder
    public JamaicaGemini(String authToken, String apiKey, String urlBase, HttpClient httpClient,
            CleverClient cleverClient) {
        if (authToken == null && apiKey == null) {
            throw new IllegalArgumentException("One of the authToken or apiKey fields must be non-null");
        }
        this.authToken = authToken;
        this.apiKey = apiKey;
        this.urlBase = Optional.ofNullable(urlBase).orElse(GEMINI_URL_BASE);
        this.httpClient = Optional.ofNullable(httpClient).orElse(HttpClient.newHttpClient());

        List<String> headers = new ArrayList<>();
        if (this.authToken == null) {
            headers.add(GEMINI_HEADER);
            headers.add(this.apiKey);
        } else {
            headers.add(AUTH_HEADER);
            headers.add(AUTH_PREFIX + this.authToken);
        }
        this.cleverClient = Optional.ofNullable(cleverClient).orElse(CleverClient.builder()
                .httpClient(this.httpClient)
                .urlBase(this.urlBase)
                .headers(headers)
                .build());
    }

    public Gemini.Models models() {
        if (modelService == null) {
            modelService = cleverClient.create(Gemini.Models.class);
        }
        return modelService;
    }
}
