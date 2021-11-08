package io.github.censodev.sdk.aniapi.v1.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationsResource {
    private List<LocalizationsResource.LocalizationResource> localizations;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocalizationResource {
        private String i18n;
        private String label;
    }
}
