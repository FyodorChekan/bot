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
import org.timetable.bot.service.GuideService;
import org.timetable.bot.service.RouteService;
import org.timetable.bot.service.yandex.YandexServiceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final CommandRegistry commandRegistry;
    private final RouteService routeService;
    private final YandexServiceImpl yandexService;
    private final GuideService guideService;

    @Autowired
    public TelegramBot(TelegramBotsApi telegramBotsApi, CommandRegistry commandRegistry, RouteService routeService, YandexServiceImpl yandexService, GuideService guideService) throws TelegramApiException {
        this.commandRegistry = commandRegistry;
        this.routeService = routeService;
        this.yandexService = yandexService;
        this.guideService = guideService;

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
        }  catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void processPlainText(Message message, String command) throws TelegramApiException, ParseException {
        String lastUserCommand = UserContext.getUserState(message.getChat().getUserName());
        if (lastUserCommand.equals(CommandConst.FIND)) {
            boolean exists = routeService.checkExistsRoute(command.split(" ")[0], command.split(" ")[1], command.split(" ")[2]);
            if (exists) {
                sendIncorrectMessage(message, routeService.getRoutesByDepartureAndArrivalAndDate(command.split(" ")[0], command.split(" ")[1], command.split(" ")[2]));
            } else {
                String city_departure = command.split(" ")[0];
                String departure_code = guideService.getGuideByCity(city_departure).getCode();
                String city_arrival = command.split(" ")[1];
                String arrival_code = guideService.getGuideByCity(city_arrival).getCode();
                String date = command.split(" ")[2];
                String body = yandexService.requestYandex(departure_code, arrival_code, date);
                sendIncorrectMessage(message, body);
            }
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
