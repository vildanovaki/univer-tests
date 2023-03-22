package com.petyes.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData {
    private int specialization_id;
    private int price_min;
    private int price_max;
    private boolean important_price;
    private ArrayList<CityData> cities;
    private ArrayList<Integer> colors;
    private int sex;
    private ArrayList<AgeRangeData> age_range;
    private boolean buy_for_free;
    private boolean is_not_for_breeding;
    private boolean views;
    private boolean has_passport;
    private boolean is_vaccined;
    private boolean is_castrated;
    private boolean is_chipped;
    private String description;
    private String get_date;
    private int breed_id;
    private String token_recaptcha;
    private int id;
}