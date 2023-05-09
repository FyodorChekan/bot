package org.timetable.bot.bot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.timetable.bot.model.UserData;
import org.timetable.bot.service.UserDataService;

import java.util.Collections;
import java.util.List;

import static org.timetable.bot.bot.text.MessageConst.HELP_MESSAGE;
import static org.timetable.bot.bot.text.MessageConst.START_MESSAGE;

@Component
@RequiredArgsConstructor
public class StartCommand implements BotCommand{

    private final UserDataService userDataService;

    @Override
    @Transactional
    public List<SendMessage> runCommand(Message message) {

        String chatId = message.getChat().getId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String login = message.getChat().getUserName();
        boolean exists = userDataService.checkExistsUser(login);

        if (!exists) {
            UserData userData = new UserData(login, chatId);
            userDataService.create(userData);

            buildResponseForNewUser(sendMessage);
        } else {
            sendMessage.setText(HELP_MESSAGE.getMessage());
        }

        return Collections.singletonList(sendMessage);
    }

    @Override
    public String supportedCommand() {
        return "/start";
    }

    private void buildResponseForNewUser(SendMessage response) {

        InlineKeyboardButton startWork = InlineKeyboardButton.builder()
                .text("Здравствуйте. Для начала работы нажмите здесь.")
                .callbackData("/start")
                .build();

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(startWork))
                .build();

        response.setText(START_MESSAGE.getMessage());
        response.setReplyMarkup(keyboardMarkup);
    }
}
