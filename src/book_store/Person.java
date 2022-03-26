package book_store;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Person extends Customer{
    private String name;
    private String surname;

    public Person(String login, String password, String email, String phone, String name, String surname) {
        super(login, password, email, phone);
        this.name = name;
        this.surname = surname;
    }

    public Person() {

    }
}
