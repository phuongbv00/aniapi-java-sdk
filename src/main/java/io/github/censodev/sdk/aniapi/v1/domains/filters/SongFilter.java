package io.github.censodev.sdk.aniapi.v1.domains.filters;

import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.SongTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SongFilter extends Filter {
    private Long animeId;
    private String title;
    private String artist;
    private Integer year;
    private AnimeSeasonEnum season;
    private SongTypeEnum type;

    @Override
    protected Map<String, String> getParamsMap() {
        var params = new HashMap<String, String>();
        params.put("anime_id", animeId.toString());
        params.put("title", title);
        params.put("artist", artist);
        params.put("year", year.toString());
        params.put("season", String.valueOf(season.ordinal()));
        params.put("type", String.valueOf(type.ordinal()));
        return params;
    }
}
