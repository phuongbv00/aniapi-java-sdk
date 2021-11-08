package io.github.censodev.sdk.aniapi.v1.domains;

import io.github.censodev.sdk.aniapi.v1.enums.AnimeFormatEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.AnimeStatusEnum;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Anime {
    private Long id;

    private Long anilistId;

    private Long malId;

    private AnimeFormatEnum format;

    private AnimeStatusEnum status;

    private Map<String, String> titles;

    private Map<String, String> descriptions;

    private Instant startDate;

    private Instant endDate;

    private AnimeSeasonEnum seasonPeriod;

    private Integer seasonYear;

    private Integer episodesCount;

    private Integer episodeDuration;

    private String trailerUrl;

    private String coverImage;

    private String coverColor;

    private String bannerImage;

    private List<String> genres;

    private Long sequel;

    private Long prequel;

    private Integer score;
}
