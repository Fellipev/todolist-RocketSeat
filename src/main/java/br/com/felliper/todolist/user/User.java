package br.com.felliper.todolist.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_users")
@Data //Anotacao para criar os Getter e Setters com Lombok
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(){}

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }


    @Override
    public String toString() {
        return "username: " + username + "\nname: " + name
                + "\npassword: " + password;
    }
}
