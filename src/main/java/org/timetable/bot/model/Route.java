package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private int number;

    @Column(name = "CREATE_REQUEST")
    private Date create_request;

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", number=" + number +
                '}';
    }
}
