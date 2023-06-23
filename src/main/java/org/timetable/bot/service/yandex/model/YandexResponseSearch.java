package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class YandexResponseSearch {

    private YandexResponseSearchItem from;

    private YandexResponseSearchItem to;

    //TODO Переделать в тип LocalDateTime или Date
    private String departure;
}
