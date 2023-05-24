package org.timetable.bot.service.yandex;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.timetable.bot.model.Route;

public interface YandexService {

    Route requestYandex(String departure, String arrival) throws JsonProcessingException;

}
