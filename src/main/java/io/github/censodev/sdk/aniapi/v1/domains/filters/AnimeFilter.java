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
        params.put("anilist_id", String.valueOf(anilistId));
        params.put("mal_id", String.valueOf(malId));
        params.put("formats", Arrays.stream(formats).map(Enum::ordinal).map(String::valueOf).collect(Collectors.joining(",")));
        params.put("status", Optional.ofNullable(status).map(Enum::ordinal).map(String::valueOf).orElse(null));
        params.put("year", String.valueOf(year));
        params.put("season",Optional.ofNullable(season).map(Enum::ordinal).map(String::valueOf).orElse(null));
        params.put("genres", String.join(",", genres));
        params.put("nsfw", String.valueOf(nsfw));
        return params;
    }
}
