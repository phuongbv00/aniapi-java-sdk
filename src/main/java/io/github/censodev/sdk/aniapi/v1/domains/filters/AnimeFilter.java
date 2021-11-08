package io.github.censodev.sdk.aniapi.v1.domains.filters;

import io.github.censodev.sdk.aniapi.v1.enums.AnimeFormatEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeStatusEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnimeFilter extends Filter {
    private String title;
    private Long anilistId;
    private Long malId;
    private AnimeFormatEnum[] formats;
    private AnimeStatusEnum status;
    private Integer year;
    private AnimeSeasonEnum season;
    private String[] genres;
    @Builder.Default
    private Boolean nsfw = true;

    @Override
    protected Map<String, String> getParamsMap() {
        var params = new HashMap<String, String>();
        params.put("title", title);
        params.put("anilist_id", anilistId.toString());
        params.put("mal_id", malId.toString());
        params.put("formats", Arrays.stream(formats).map(AnimeFormatEnum::ordinal).map(Objects::toString).collect(Collectors.joining(",")));
        params.put("status", String.valueOf(status.ordinal()));
        params.put("year", year.toString());
        params.put("season", String.valueOf(season.ordinal()));
        params.put("genres", String.join(",", genres));
        params.put("nsfw", nsfw.toString());
        return params;
    }
}
