package io.github.censodev.sdk.aniapi.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.censodev.sdk.aniapi.v1.domains.*;
import io.github.censodev.sdk.aniapi.v1.domains.filters.AnimeFilter;
import io.github.censodev.sdk.aniapi.v1.domains.filters.EpisodeFilter;
import io.github.censodev.sdk.aniapi.v1.domains.filters.SongFilter;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AniApiClient {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final String ENDPOINT = "https://api.aniapi.com/v1/";

    @SneakyThrows
    private <T> CompletableFuture<T> fetch(String method, String uri, Object body, TypeReference<T> resTypeRef) {
        var mapper = JsonMapper.builder()
                .findAndAddModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + uri))
                .header("Content-Type", "application/json")
                .method(method,
                        Optional.ofNullable(body)
                                .map(b -> {
                                    try {
                                        return mapper.writeValueAsString(b);
                                    } catch (JsonProcessingException ignored) {
                                        return null;
                                    }
                                })
                                .map(HttpRequest.BodyPublishers::ofString)
                                .orElse(HttpRequest.BodyPublishers.noBody()))
                .build();
        return httpClient
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(res -> {
                    try {
                        return mapper.readValue(res.body(), resTypeRef);
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                });
    }

    private <T> CompletableFuture<T> fetch(String uri, TypeReference<T> resTypeRef) {
        return fetch("GET", uri, null, resTypeRef);
    }

    public CompletableFuture<ApiResponse<Anime>> getAnime(Long id) {
        return fetch("anime/" + id, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Pagination<Anime>>> getAnimeList(AnimeFilter filter) {
        return fetch("anime" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Episode>> getEpisode(Long id) {
        return fetch("episode/" + id, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Pagination<Episode>>> getEpisodeList(EpisodeFilter filter) {
        return fetch("episode" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Song>> getSong(Long id) {
        return fetch("song/" + id, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Pagination<Song>>> getSongList(SongFilter filter) {
        return fetch("song" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<GenresResource>> getGenres(String version) {
        return fetch(String.format("resources/%s/0", version), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<GenresResource>> getGenres() {
        return getGenres("1.0");
    }

    public CompletableFuture<ApiResponse<LocalizationsResource>> getLocalizations(String version) {
        return fetch(String.format("resources/%s/1", version), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<LocalizationsResource>> getLocalizations() {
        return getLocalizations("1.0");
    }
}
