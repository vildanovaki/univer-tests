package com.petyes.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/simple.properties"
})
public interface AppConfig extends Config {

    String webUrl();
    String apiUrl();
    String breederPhoneNumber();
    String customerPhoneNumber();
    String userPassword();
    String wrongPassword();
    int specialization();
    int breed();
    String token_recapcha();
    int requestId();
}