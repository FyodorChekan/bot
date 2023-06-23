package org.timetable.bot.service.yandex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class YandexBaseResponse {

    private YandexResponseSearch search;

    @NotNull
    private List<YandexResponseSegments> segments;

    @JsonProperty("interval_segments")
    private List<YandexResponseIntervalSegments> intervalSegments;

    private YandexResponsePagination pagination;

    public YandexBaseResponse(YandexResponseSearch search, List<YandexResponseSegments> segments, List<YandexResponseIntervalSegments> intervalSegments, YandexResponsePagination pagination) {
        this.search = search;
        this.segments = segments;
        this.intervalSegments = intervalSegments;
        this.pagination = pagination;
    }

    public YandexBaseResponse() {
    }
}
