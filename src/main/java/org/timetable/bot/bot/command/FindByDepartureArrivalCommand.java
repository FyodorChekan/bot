package org.timetable.bot.bot.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.timetable.bot.service.yandex.YandexService;

import java.util.Collections;
import java.util.List;

@Service
public class FindByDepartureArrivalCommand implements BotCommand{

    private final YandexService yandexService;

    public FindByDepartureArrivalCommand(YandexService yandexService) {
        this.yandexService = yandexService;
    }

    @Override
    public List<SendMessage> runCommand(Message message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChat().getId().toString());

        sendMessage.setText("Введите станцию отправления, станцию прибытия через пробел в формате: \"Город_отправления Город_прибытия Дата\"");

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder().build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return Collections.singletonList(sendMessage);

    }

    @Override
    public String supportedCommand() {
        return CommandConst.FIND_BY_DEPARTURE_ARRIVAL;
    }
}
