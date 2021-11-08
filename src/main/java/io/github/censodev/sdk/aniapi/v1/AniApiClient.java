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

public class AniApiClient {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final String ENDPOINT = "https://api.aniapi.com/v1/";

    @SneakyThrows
    private <T> T fetch(String method, String uri, Object body, TypeReference<T> resTypeRef) {
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
                                    }
                                    return null;
                                })
                                .map(HttpRequest.BodyPublishers::ofString)
                                .orElse(HttpRequest.BodyPublishers.noBody()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), resTypeRef);
    }

    private <T> T fetch(String uri, TypeReference<T> resTypeRef) {
        return fetch("GET", uri, null, resTypeRef);
    }

    public ApiResponse<Anime> getAnime(Long id) {
        return fetch("anime/" + id, new TypeReference<>() {
        });
    }

    public ApiResponse<Pagination<Anime>> getAnimeList(AnimeFilter filter) {
        return fetch("anime" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public ApiResponse<Episode> getEpisode(Long id) {
        return fetch("episode/" + id, new TypeReference<>() {
        });
    }

    public ApiResponse<Pagination<Episode>> getEpisodeList(EpisodeFilter filter) {
        return fetch("episode" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public ApiResponse<Song> getSong(Long id) {
        return fetch("song/" + id, new TypeReference<>() {
        });
    }

    public ApiResponse<Pagination<Song>> getSongList(SongFilter filter) {
        return fetch("song" + filter.toQueryString(), new TypeReference<>() {
        });
    }
}
