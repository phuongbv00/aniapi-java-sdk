package io.github.censodev.sdk.aniapi.v1.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {
    private Integer currentPage;

    private Integer count;

    private List<T> documents;

    private Integer lastPage;
}
