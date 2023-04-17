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
            /find_route - поиск поездов по маршруту
            /find_number - поиск маршрута поезда по номеру
            /by_departure - поиск поездов по городу отправления
            /by_arrival - поиск поездов по городу прибытия
            """);

    private final String message;

    MessageConst(String message) {
        this.message = message;
    }
}
