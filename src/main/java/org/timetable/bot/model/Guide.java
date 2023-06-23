package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "GUIDE")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "CODE")
    private String code;

    public Guide() {
    }

    public Guide(String city, String code) {
        this.city = city;
        this.code = code;
    }
}
