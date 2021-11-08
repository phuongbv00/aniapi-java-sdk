package io.github.censodev.sdk.aniapi.v1;

import io.github.censodev.sdk.aniapi.v1.domains.Pagination;
import io.github.censodev.sdk.aniapi.v1.domains.filters.AnimeFilter;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeFormatEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeStatusEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AniApiClientTest {
    AniApiClient client = new AniApiClient();

    @Test
    void testAnimeList() {
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
                .build();
        var res = client.getAnimeList(filter);
        assertNotNull(res);
        assertNotNull(res.getData());
        assertTrue(res.getData() instanceof Pagination);
        System.out.println(res.getData().getDocuments());
    }
}
