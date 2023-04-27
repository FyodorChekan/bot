package org.timetable.bot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

@Component
public class HelpCommand implements BotCommand{
    @Override
    public List<SendMessage> runCommand(Message message) {

        SendMessage helpMessage = new SendMessage();
        helpMessage.setChatId(message.getChat().getId().toString());
        helpMessage.setText("""
                Доступные команды:
                /find_by_departure_arrival - Начать поиск поездов по маршруту.
                /find_by_number - Начать поиск маршрута по номеру поезда.
                /find_by_departure_date - Начать поиск маршрута по городу отправления и дате отправления.
                /find_by_arrival_date - Начать поиск маршрута по городу прибытия и дате прибытия.
                /find_information_by_number - Получить детальную информацию по поезду.
                """);
        setDefaultKeyboard(helpMessage);

        return Collections.singletonList(helpMessage);
    }

    private void setDefaultKeyboard(SendMessage response) {

        InlineKeyboardButton findByDepartureArrival = InlineKeyboardButton.builder()
                .text("Найди по маршруту.")
                .callbackData("/find_by_departure_arrival")
                .build();

        InlineKeyboardButton findByNumber = InlineKeyboardButton.builder()
                .text("Найди по номеру поезда.")
                .callbackData("/find_by_number")
                .build();

        InlineKeyboardButton findByDepartureDate = InlineKeyboardButton.builder()
                .text("Найди по городу отправления и дате.")
                .callbackData("/find_by_departure_date")
                .build();

        InlineKeyboardButton findByArrivalDate = InlineKeyboardButton.builder()
                .text("Найди по городу прибытия и дате.")
                .callbackData("/find_by_arrival_date")
                .build();

        InlineKeyboardButton findInformationByNumber = InlineKeyboardButton.builder()
                .text("Введи номер поезда и узнай больше.")
                .callbackData("/find_information_by_number")
                .build();

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(findByDepartureArrival))
                .keyboardRow(List.of(findByNumber))
                .keyboardRow(List.of(findByDepartureDate))
                .keyboardRow(List.of(findByArrivalDate))
                .keyboardRow(List.of(findInformationByNumber))
                .build();

        response.setReplyMarkup(keyboardMarkup);
    }

    @Override
    public String supportedCommand() {
        return "/help";
    }
}
