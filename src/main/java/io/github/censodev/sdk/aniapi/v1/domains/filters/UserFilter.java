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
public class UserFilter extends Filter {
    private String username;
    private String email;
    
    @Override
    protected Map<String, String> getParamsMap() {
        var params = new HashMap<String, String>();
        params.put("username", username);
        params.put("email", email);
        return params;
    }
}
