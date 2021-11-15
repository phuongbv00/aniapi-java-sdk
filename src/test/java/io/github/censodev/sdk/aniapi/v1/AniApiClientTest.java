package io.github.censodev.sdk.aniapi.v1;

import io.github.censodev.sdk.aniapi.v1.domains.Anime;
import io.github.censodev.sdk.aniapi.v1.domains.Pagination;
import io.github.censodev.sdk.aniapi.v1.domains.filters.AnimeFilter;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeFormatEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeStatusEnum;
import io.github.censodev.sdk.aniapi.v1.enums.SortDirectionEnum;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class AniApiClientTest {
    AniApiClient client = new AniApiClient();

    @Test
    void testAnimeList() throws ExecutionException, InterruptedException {
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
        assertNotNull(res);
        assertNotNull(res.getData());
        assertTrue(res.getData() instanceof Pagination);
        System.out.println(res.getData().getDocuments());
    }

    @Test
    void testAnime() throws ExecutionException, InterruptedException {
        var res = client.getAnime(1L).get();
        assertNotNull(res);
        assertNotNull(res.getData());
        assertTrue(res.getData() instanceof Anime);
        System.out.println(res.getData());
    }
}
