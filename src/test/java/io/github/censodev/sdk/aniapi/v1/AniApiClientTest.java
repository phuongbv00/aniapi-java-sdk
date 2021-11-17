package io.github.censodev.sdk.aniapi.v1;

import io.github.censodev.sdk.aniapi.v1.domains.filters.AnimeFilter;
import io.github.censodev.sdk.aniapi.v1.domains.filters.EpisodeFilter;
import io.github.censodev.sdk.aniapi.v1.domains.filters.SongFilter;
import io.github.censodev.sdk.aniapi.v1.enums.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class AniApiClientTest {
    AniApiClient client = AniApiClient.builder()
            .build();

    @Test
    void getAnimeList200() throws ExecutionException, InterruptedException {
        var filter = AnimeFilter
                .builder()
                .title("Cowboy Bebop")
                .anilistId(1L)
                .malId(1L)
                .formats(new AnimeFormatEnum[]{AnimeFormatEnum.TV, AnimeFormatEnum.TV_SHORT})
                .status(AnimeStatusEnum.FINISHED)
                .year(1998)
                .season(AnimeSeasonEnum.SPRING)
                .genres(new String[]{"Action", "Guns", "Military"})
                .sort("titles.en", SortDirectionEnum.DESCENDING)
                .build();
        var res = client.getAnimeList(filter).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        System.out.println(res.getData().getDocuments());
    }

    @Test
    void getAnime200() throws ExecutionException, InterruptedException {
        var id = 1L;
        var res = client.getAnime(id).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        assertEquals(id, res.getData().getId(), "Id must be " + id);
        System.out.println(res.getData());
    }

    @Test
    void getEpisodeList200() throws ExecutionException, InterruptedException {
        var filter = EpisodeFilter
                .builder()
                .animeId(1L)
                .source("gogoanime")
                .locale("en")
                .sort("number", SortDirectionEnum.DESCENDING)
                .build();
        var res = client.getEpisodeList(filter).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        System.out.println(res.getData().getDocuments());
    }

    @Test
    void getEpisode200() throws ExecutionException, InterruptedException {
        var id = 1L;
        var res = client.getEpisode(id).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        assertEquals(id, res.getData().getId(), "Id must be " + id);
        System.out.println(res.getData());
    }

    @Test
    void getGenres200() throws ExecutionException, InterruptedException {
        var res = client.getGenres().get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        System.out.println(res.getData());
    }

    @Test
    void getLocalizations200() throws ExecutionException, InterruptedException {
        var res = client.getLocalizations().get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        System.out.println(res.getData());
    }

    @Test
    void getSongList200() throws ExecutionException, InterruptedException {
        var filter = SongFilter
                .builder()
                .animeId(10L)
                .type(SongTypeEnum.OPENING)
                .sort("year", SortDirectionEnum.DESCENDING)
                .sort("season", SortDirectionEnum.ASCENDING)
                .build();
        var res = client.getSongList(filter).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        System.out.println(res.getData().getDocuments());
    }

    @Test
    void getSong200() throws ExecutionException, InterruptedException {
        var id = 1L;
        var res = client.getSong(id).get();
        assertNotNull(res, "Response must be not null");
        assertEquals(200, res.getStatusCode(), "Status code must be 200");
        assertNotNull(res.getData(), "Data must be not null");
        assertEquals(id, res.getData().getId(), "Id must be " + id);
        System.out.println(res.getData());
    }
}
