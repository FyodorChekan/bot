package org.timetable.bot.service.yandex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.service.yandex.model.YandexBaseResponse;

@Service
@Profile("PROM")
public class YandexServiceImpl extends BaseYandexServiceImpl {

    protected YandexServiceImpl(RouteRepo repo) {
        super(repo);
    }

    @Override
    public Route requestYandex(String departure, String arrival) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.rasp.yandex.net/v3.0/search/?apikey=2263b4b1-d9e2-4441-8d89-45a44a67fe58&format=json&from=c146&to=c213&lang=ru_RU&page=1&date=2015-09-02";
        ResponseEntity<String> request = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(request.getBody());

        Gson gson = new Gson();
        YandexBaseResponse response = gson.fromJson(String.valueOf(request), YandexBaseResponse.class);


        Route route = new Route();
        saveRoute(route);

        return route;
    }
}
