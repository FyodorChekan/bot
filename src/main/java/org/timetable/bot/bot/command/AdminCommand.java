package org.timetable.bot.bot.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Collections;
import java.util.List;

@Service
public class AdminCommand implements BotCommand{
    @Override
    public List<SendMessage> runCommand(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChat().getId().toString());

        sendMessage.setText("Введите через пробел город и кодировку: ");

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder().build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return Collections.singletonList(sendMessage);
    }

    @Override
    public String supportedCommand() {
        return CommandConst.ADMIN;
    }
}
