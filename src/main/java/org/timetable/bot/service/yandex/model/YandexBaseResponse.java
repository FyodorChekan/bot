package org.timetable.bot.service.yandex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class YandexBaseResponse {

    private YandexResponseSearch search;

    private List<YandexResponseSegments> segments;

    @JsonProperty("interval_segments")
    private List<YandexResponseIntervalSegments> intervalSegments;

    private YandexResponsePagination pagination;
}
