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
            /find - Начать поиск поездов по маршруту.
            """);

    private final String message;

    MessageConst(String message) {
        this.message = message;
    }
}
