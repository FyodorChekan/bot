package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponseThread {

    private String number;

    private String title;

    private String short_title;

    private String express_type;

    private String transport_type;

    private YandexResponseCarrier carrier;

    private String uid;

    private String vehicle;

    private YandexResponseTransportSubtype transport_subtype;

    private String thread_method_link;
}
