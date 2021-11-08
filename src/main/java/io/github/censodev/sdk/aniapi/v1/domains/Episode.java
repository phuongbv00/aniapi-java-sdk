package io.github.censodev.sdk.aniapi.v1.domains;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    private Long id;

    private Long animeId;

    private Integer number;

    private String title;

    private String video;

    private String source;

    private String locale;
}
