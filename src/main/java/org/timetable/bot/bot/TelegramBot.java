package org.timetable.bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.timetable.bot.bot.command.CommandRegistry;

import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final CommandRegistry commandRegistry;

    @Autowired
    public TelegramBot(TelegramBotsApi telegramBotsApi, CommandRegistry commandRegistry) throws TelegramApiException {
        this.commandRegistry = commandRegistry;

        telegramBotsApi.registerBot(this);

    }

    @Override
    public String getBotUsername() {
        return "TimetableRZDBot";
    }

    @Override
    public String getBotToken() {
        return "6030133906:AAE6kwsqpo3o338LzMoaQy3-vZ446kv4fy0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {

            Message message;
            if (update.getMessage() != null) {
                message = update.getMessage();
            } else {
                message = update.getCallbackQuery().getMessage();
            }

            String command;

            if (update.getMessage() == null) {
                command = update.getCallbackQuery().getData();
            } else {
                command = update.getMessage().getText();
            }

            if (!command.startsWith("/")) {
                SendMessage incorrectCommandMessage = new SendMessage();
                incorrectCommandMessage.setChatId(message.getChat().getId().toString());
                incorrectCommandMessage.setText("Пожалуйста, введите команду. Узнать список доступных команд - /help");
                execute(incorrectCommandMessage);
                return;
            }

            commandRegistry.getCommand(command).runCommand(message).forEach(result -> {
                try {
                    setDefaultKeyboard(result);
                    execute(result);
                } catch (TelegramApiException e) {
                    log.error("Ошибка отправки ответа", e);
                }
            });
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки ответа", e);
        }
    }

    private void setDefaultKeyboard(SendMessage response) {

        if (response.getReplyMarkup() != null) {
            return;
        }

        InlineKeyboardButton help = InlineKeyboardButton.builder()
                .text("Доступные команды.")
                .callbackData("/help")
                .build();

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(help))
                .build();

        response.setReplyMarkup(keyboardMarkup);
    }
}
