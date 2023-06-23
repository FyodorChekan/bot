package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponseSegments {

    private YandexResponseThread thread;

    private YandexResponseSearchItem from;

    private YandexResponseSearchItem to;

    private String departure;
}
