package io.github.censodev.sdk.aniapi.v1.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenresResource {
    private List<String> genres;
}
