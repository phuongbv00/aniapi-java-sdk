package io.github.censodev.sdk.aniapi.v1.domains.filters;

import io.github.censodev.sdk.aniapi.v1.enums.SortDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Filter {
    private List<Long> ids;

    @Singular("sort")
    private Map<String, SortDirectionEnum> sortMap;

    protected abstract Map<String, String> getParamsMap();

    public String toQueryString() {
        var params = getParamsMap();
        if (ids != null && !ids.isEmpty()) {
            params.put("ids", ids.stream().map(Object::toString).collect(Collectors.joining(",")));
        }
        if (!sortMap.isEmpty()) {
            params.put("sort_fields", String.join(",", sortMap.keySet()));
            params.put("sort_directions", sortMap.values()
                    .stream()
                    .map(SortDirectionEnum::getValue)
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }
        return params.isEmpty()
                ? ""
                : "?" + params.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !entry.getValue().isBlank())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .map(q -> URLEncoder.encode(q, StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
