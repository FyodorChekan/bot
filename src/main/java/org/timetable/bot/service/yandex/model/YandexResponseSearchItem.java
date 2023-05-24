package org.timetable.bot.service.yandex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponseSearchItem {

    private String type;

    private String title;

    @JsonProperty("short_title")
    private String shortTitle;

    @JsonProperty("popular_title")
    private String popularTitle;

    private String code;
}
