package book_store;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
abstract public class Customer extends  User {
    private String email;
    private String phone;
    //private CardInfo card;


    public Customer(String login, String password, String email, String phone) {
        super(login, password);
        this.email = email;
        this.phone = phone;
    }

    public Customer() {

    }
}
