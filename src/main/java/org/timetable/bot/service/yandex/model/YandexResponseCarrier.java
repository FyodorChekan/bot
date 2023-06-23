package org.timetable.bot.service.yandex.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YandexResponseCarrier {

    private Integer code;

    private String title;

    private YandexResponseCodes codes;

    private String address;

    private String url;

    private String email;

    private String contacts;

    private String phone;

    private String logo;

    private String logo_svg;
}
