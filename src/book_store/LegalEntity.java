package book_store;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class LegalEntity extends Customer{
    private String companyName;

    public LegalEntity(String login, String password, String email, String phone, String companyName) {
        super(login, password, email, phone);
        this.companyName = companyName;
    }

    public LegalEntity() {

    }
}
