package org.timetable.bot.service.yandex;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.service.yandex.model.YandexBaseResponse;
import org.timetable.bot.service.yandex.model.YandexResponseSegments;
import org.timetable.bot.service.yandex.model.YandexResponseThread;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

@Component
@Service
@Profile("PROM")
public class YandexServiceImpl extends BaseYandexServiceImpl {


    protected YandexServiceImpl(RouteRepo repo) {
        super(repo);
    }

    @Override
    public String requestYandex(String departure, String arrival, String date_route) throws ParseException {

        StringBuilder stringBuilder = new StringBuilder();


        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.rasp.yandex.net/v3.0/search/?apikey=2263b4b1-d9e2-4441-8d89-45a44a67fe58&format=json&from=" + departure + "&to=" + arrival + "&lang=ru_RU&page=1&date=" + date_route + "&transport_types=train";
        ResponseEntity<YandexBaseResponse> response = restTemplate.getForEntity(url, YandexBaseResponse.class);

        YandexBaseResponse responseBody = response.getBody();

        if (responseBody == null) {
            throw new RuntimeException();
        }

        stringBuilder.append("Найденные маршруты: \n");

        for(YandexResponseSegments segments : response.getBody().getSegments()) {

            YandexResponseThread thread = segments.getThread();

            String dep = segments.getFrom().getTitle();
            String arr = segments.getTo().getTitle();
            String num = thread.getNumber();
            String dateString = segments.getDeparture().split(Pattern.quote("+"))[0];
            LocalDateTime dateTime = LocalDateTime.parse(dateString);
            Date date = Timestamp.valueOf(dateTime);
            Date create_request = new Date();

            Route route = new Route();
            route.setCreate_request(create_request);
            route.setDeparture(dep);
            route.setArrival(arr);
            route.setNumber(num);
            route.setDate(date);
            saveRoute(route);
            stringBuilder.append(route.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
