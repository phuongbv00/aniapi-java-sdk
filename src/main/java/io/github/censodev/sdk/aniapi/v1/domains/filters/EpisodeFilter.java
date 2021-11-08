package io.github.censodev.sdk.aniapi.v1.domains.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeFilter extends Filter {
    private Long animeId;
    private Integer number;
    private String source;
    private String locale;

    @Override
    protected Map<String, String> getParamsMap() {
        var params = new HashMap<String, String>();
        params.put("anime_id", animeId.toString());
        params.put("number", number.toString());
        params.put("source", source);
        params.put("locale", locale);
        return params;
    }
}
