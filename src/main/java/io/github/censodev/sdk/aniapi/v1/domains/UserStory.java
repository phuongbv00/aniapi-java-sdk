package io.github.censodev.sdk.aniapi.v1.domains;

import io.github.censodev.sdk.aniapi.v1.enums.UserStoryStatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserStory {
    private Long id;

    private Long userId;

    private Long animeId;

    private UserStoryStatusEnum status;

    private Integer currentEpisode;

    private Long currentEpisodeTicks;
}
