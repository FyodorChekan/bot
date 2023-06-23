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
                /find - Начать поиск поездов по маршруту.
                """);
        setDefaultKeyboard(helpMessage);

        return Collections.singletonList(helpMessage);
    }

    private void setDefaultKeyboard(SendMessage response) {

        InlineKeyboardButton find = InlineKeyboardButton.builder()
                .text("Найди по маршруту.")
                .callbackData(CommandConst.FIND)
                .build();

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(find))
                .build();

        response.setReplyMarkup(keyboardMarkup);
    }

    @Override
    public String supportedCommand() {
        return "/help";
    }
}
