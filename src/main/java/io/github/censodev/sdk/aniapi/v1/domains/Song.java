package io.github.censodev.sdk.aniapi.v1.domains;

import io.github.censodev.sdk.aniapi.v1.enums.AnimeSeasonEnum;
import io.github.censodev.sdk.aniapi.v1.enums.SongTypeEnum;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private Long id;

    private Long animeId;

    private String title;

    private String artist;

    private String album;

    private Integer year;

    private AnimeSeasonEnum season;

    private Integer duration;

    private String previewUrl;

    private String openSpotifyUrl;

    private String localSpotifyUrl;

    private SongTypeEnum songType;
}
