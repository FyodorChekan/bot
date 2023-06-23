package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponsePagination {

    private Integer total;

    private Integer limit;

    private Integer offset;
}
