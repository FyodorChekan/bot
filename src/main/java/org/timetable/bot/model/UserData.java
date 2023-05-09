package org.timetable.bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USERDATA")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "CHAT_ID")
    private String chatId;

    @Column(name = "IS_ADMIN")
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<UserRequest> requests;

    public UserData() {
    }

    public UserData(String login, String chatId) {
        this.login = login;
        this.chatId = chatId;
    }
}
