package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER_REQUEST")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserData user;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @Column(name = "REQUEST_DATE")
    private Date request_date;

    @Column(name = "FAVOURITE")
    private boolean favourite;

    public UserRequest() {
    }
}
