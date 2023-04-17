package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    @ManyToOne
    @JoinColumn(name = "USER_CHAT_ID")
    private UserData userData;
}
