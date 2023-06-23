package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ROUTE")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DEPARTURE")
    private String departure;

    @Column(name = "ARRIVAL")
    private String arrival;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "CREATE_REQUEST")
    private Date create_request;

    public Route() {
    }

    @Override
    public String toString() {
        return "Маршрут №" + number + ". Город отправления: " + departure + ". Город прибытия: " + arrival + ". Дата отправления поезда: " + date + ".\n";
    }
}
