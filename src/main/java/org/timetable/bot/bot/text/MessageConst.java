package org.timetable.bot.bot.text;

import lombok.Getter;

@Getter
public enum MessageConst {
    START_MESSAGE("""
            Здравствуйте!
            Для начала работы введите /start.
            """),
    HELP_MESSAGE("""
            Доступные команды:
            /find_by_departure_arrival - Начать поиск поездов по маршруту.
            /find_by_number - Начать поиск маршрута по номеру поезда.
            /find_by_departure_date - Начать поиск маршрута по городу отправления и дате отправления.
            /find_by_arrival_date - Начать поиск маршрута по городу прибытия и дате прибытия.
            /find_information_by_number - Получить детальную информацию по поезду.
            """);

    private final String message;

    MessageConst(String message) {
        this.message = message;
    }
}
