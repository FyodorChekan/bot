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
import org.timetable.bot.bot.command.CommandConst;
import org.timetable.bot.bot.command.CommandRegistry;
import org.timetable.bot.context.UserContext;
import org.timetable.bot.service.FindByDepartureArrivalService;
import org.timetable.bot.service.RouteService;
import org.timetable.bot.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final CommandRegistry commandRegistry;
    private final RouteService routeService;

    @Autowired
    public TelegramBot(TelegramBotsApi telegramBotsApi, CommandRegistry commandRegistry, RouteService routeService) throws TelegramApiException {
        this.commandRegistry = commandRegistry;
        this.routeService = routeService;

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

            Message message = getMessage(update);
            String command = getCommand(update);

            if (command.startsWith("/")) {
                commandRegistry.getCommand(command).runCommand(message).forEach(result -> {
                    try {
                        setDefaultKeyboard(result);
                        execute(result);

                        UserContext.saveUserState(command, message.getChat().getUserName());

                    } catch (TelegramApiException e) {
                        log.error("Ошибка отправки ответа", e);
                    }
                });
                return;
            }

            processPlainText(message, command);

        } catch (TelegramApiException e) {
            log.error("Ошибка отправки ответа", e);
        }
    }

    private void processPlainText(Message message, String command) throws TelegramApiException {
        String lastUserCommand = UserContext.getUserState(message.getChat().getUserName());
        if (lastUserCommand.equals(CommandConst.FIND_BY_DEPARTURE_ARRIVAL)) {

            try {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = dateFormat.parse(command.split(" ")[2]);
                Date tomorrow = DateUtils.getNextDay(date);
                Date routeDate = DateUtils.addHours(date, 5);

                if (routeDate.after(date) && routeDate.before(tomorrow)) {
                    sendIncorrectMessage(message, FindByDepartureArrivalService.find(command.split(" ")[0], command.split(" ")[1], routeDate));
                }

            } catch (ParseException e) {
                sendIncorrectMessage(message, "Ошибка в формате даты. Формат: \"dd.MM.yyyy\"");
            }
        } else if (lastUserCommand.equals(CommandConst.FIND_BY_NUMBER)) {
            sendIncorrectMessage(message, routeService.getRouteByNumber(Integer.parseInt(message.getText())).toString());
        }
    }

    private static String getCommand(Update update) {
        String command;
        if (update.getMessage() == null) {
            command = update.getCallbackQuery().getData();
        } else {
            command = update.getMessage().getText();
        }
        return command;
    }

    private static Message getMessage(Update update) {
        Message message;
        if (update.getMessage() != null) {
            message = update.getMessage();
        } else {
            message = update.getCallbackQuery().getMessage();
        }
        return message;
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

    private void sendIncorrectMessage(Message message, String text) throws TelegramApiException {
        SendMessage incorrectCommandMessage = new SendMessage();
        incorrectCommandMessage.setChatId(message.getChat().getId().toString());
        incorrectCommandMessage.setText(text);
        execute(incorrectCommandMessage);
    }
}
