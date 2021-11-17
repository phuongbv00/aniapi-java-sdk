package io.github.censodev.sdk.aniapi.v1.domains;

import io.github.censodev.sdk.aniapi.v1.enums.UserGenderEnum;
import io.github.censodev.sdk.aniapi.v1.enums.UserRoleEnum;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String username;

    private String email;

    private String password;

    private Boolean emailVerified;

    private Instant lastLoginDate;

    private String accessToken;

    private UserRoleEnum role;

    private UserGenderEnum gender;

    private String avatar;

    private String localization;

    private Long anilistId;

    private String anilistToken;

    private Boolean hasAnilist;

    private Long malId;

    private String malToken;

    private Boolean hasMal;
}
