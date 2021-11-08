package io.github.censodev.sdk.aniapi.v1.enums;

import lombok.Getter;

@Getter
public enum SortDirectionEnum {
    ASCENDING(1),
    DESCENDING(-1);

    private final Integer value;

    SortDirectionEnum(Integer i) {
        value = i;
    }
}
