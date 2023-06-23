package org.timetable.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.timetable.bot.service.yandex.model.YandexBaseResponse;

@SpringBootTest
class BotApplicationTests {

	@Test
	public void invokeYandexTest() throws JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.rasp.yandex.net/v3.0/search/?apikey=2263b4b1-d9e2-4441-8d89-45a44a67fe58&format=json&from=c213&to=c4&lang=ru_RU&page=1&date=2023-06-12";
		ResponseEntity<YandexBaseResponse> response = restTemplate.getForEntity(url, YandexBaseResponse.class);

		Assertions.assertEquals(response.getBody().getSearch().getDate(), "2023-06-12");

	}

}
