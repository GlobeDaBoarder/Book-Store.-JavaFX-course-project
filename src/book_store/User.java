package book_store;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
abstract public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String password;
    private LocalDate createDate;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.createDate = LocalDate.now();
    }

    public User() {
    }
}
