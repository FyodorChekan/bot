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
import org.timetable.bot.context.UserContext;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.repo.UserDataRepo;
import org.timetable.bot.repo.UserRequestRepo;
import org.timetable.bot.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

                String lastUserCommand = UserContext.getUserState(message.getChat().getUserName());
                if (lastUserCommand.equals("/find_by_departure_arrival")) {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    SendMessage incorrectCommandMessage = new SendMessage();
                    incorrectCommandMessage.setChatId(message.getChat().getId().toString());

                    try {

                        Date date = dateFormat.parse(command.split(" ")[2]);

                        Calendar tomorrowCalendar = Calendar.getInstance();
                        tomorrowCalendar.setTime(date);
                        tomorrowCalendar.add(Calendar.DAY_OF_WEEK, 1);
                        Date tomorrow = tomorrowCalendar.getTime();

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.HOUR, 5);
                        Date routeDate = calendar.getTime();

                        if (routeDate.after(date) && routeDate.before(tomorrow)) {
                            incorrectCommandMessage.setText(FindByDepartureArrivalService.find(command.split(" ")[0], command.split(" ")[1], routeDate));
                            execute(incorrectCommandMessage);
                        }

                    } catch(ParseException e) {
                        incorrectCommandMessage.setChatId(message.getChat().getId().toString());
                        incorrectCommandMessage.setText("Ошибка в формате даты. Формат: \"dd.MM.yyyy\"");
                        execute(incorrectCommandMessage);
                    }
                    return;
                } else if (lastUserCommand.equals("/find_by_number")) {
                    String login = message.getChat().getUserName();
                    SendMessage incorrectCommandMessage = new SendMessage();
                    incorrectCommandMessage.setChatId(message.getChat().getId().toString());
                    DevDBServiceImpl dbService = null;
                    incorrectCommandMessage.setText(dbService.findRoute(message.getText()).toString());
                    execute(incorrectCommandMessage);
                    return;
                }


                return;
            }

            commandRegistry.getCommand(command).runCommand(message).forEach(result -> {
                try {
                    setDefaultKeyboard(result);
                    execute(result);

                    UserContext.saveUserState(command, message.getChat().getUserName());

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
