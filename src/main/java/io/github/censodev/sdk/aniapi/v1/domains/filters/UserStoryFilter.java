package io.github.censodev.sdk.aniapi.v1.domains.filters;

import io.github.censodev.sdk.aniapi.v1.enums.UserStoryStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserStoryFilter extends Filter {
    private Long animeId;
    private Long userId;
    private UserStoryStatusEnum status;
    private Boolean synced;
    
    @Override
    protected Map<String, String> getParamsMap() {
        var params = new HashMap<String, String>();
        params.put("anime_id", String.valueOf(animeId));
        params.put("user_id", String.valueOf(userId));
        params.put("status", Optional.ofNullable(status).map(Enum::ordinal).map(String::valueOf).orElse(null));
        params.put("synced", String.valueOf(synced));
        return params;
    }
}
