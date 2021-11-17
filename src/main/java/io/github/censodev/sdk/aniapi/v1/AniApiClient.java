package io.github.censodev.sdk.aniapi.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.censodev.sdk.aniapi.v1.domains.*;
import io.github.censodev.sdk.aniapi.v1.domains.filters.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Builder
@AllArgsConstructor
public class AniApiClient {
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final String ENDPOINT = "https://api.aniapi.com/v1/";

    private String token;

    @Builder.Default
    private Logger logger = LoggerFactory.getLogger(AniApiClient.class);

    @SneakyThrows
    private <T> CompletableFuture<T> fetch(String method, String uri, Object body, TypeReference<T> resTypeRef) {
        var mapper = JsonMapper.builder()
                .findAndAddModules()
                .enable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + uri))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .method(method,
                        Optional.ofNullable(body)
                                .map(b -> {
                                    try {
                                        return mapper.writeValueAsString(b);
                                    } catch (JsonProcessingException e) {
                                        logger.error(e.getMessage());
                                        return null;
                                    }
                                })
                                .map(HttpRequest.BodyPublishers::ofString)
                                .orElse(HttpRequest.BodyPublishers.noBody()))
                .build();
        return HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(res -> {
                    try {
                        return mapper.readValue(res.body(), resTypeRef);
                    } catch (JsonProcessingException e) {
                        logger.error(e.getMessage());
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

    public CompletableFuture<ApiResponse<User>> getMe() {
        return fetch("auth/me", new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Pagination<User>>> getUserList(UserFilter filter) {
        return fetch("user" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<User>> getUser(Long id) {
        return fetch("user/" + id, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<User>> updateUser(User model) {
        return fetch("POST", "user", model, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<String>> deleteUser(Long id) {
        return fetch("DELETE", "user/" + id, null, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<Pagination<UserStory>>> getUserStoryList(UserStoryFilter filter) {
        return fetch("user_story" + filter.toQueryString(), new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<UserStory>> getUserStory(Long id) {
        return fetch("user_story/" + id, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<UserStory>> createUserStory(UserStory model) {
        return fetch("PUT", "user_story", model, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<UserStory>> updateUserStory(UserStory model) {
        return fetch("POST", "user_story", model, new TypeReference<>() {
        });
    }

    public CompletableFuture<ApiResponse<String>> deleteUserStory(Long id) {
        return fetch("DELETE", "user_story/" + id, null, new TypeReference<>() {
        });
    }
}
