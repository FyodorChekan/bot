package org.timetable.bot.service.yandex;

import java.text.ParseException;

public interface YandexService {

    String requestYandex(String departure, String arrival, String date_arrival) throws ParseException;

}
