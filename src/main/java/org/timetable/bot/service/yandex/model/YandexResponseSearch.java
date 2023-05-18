package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponseSearch {

    private YandexResponseSearchItem from;

    private YandexResponseSearchItem to;

    //TODO Переделать в тип LocalDateTime или Date
    private String date;
}
