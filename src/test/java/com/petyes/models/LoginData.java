package com.petyes.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginData {
    private String phone;
    private String password;
    private boolean remember;
    private String token;
    private String token_recaptcha;
}
