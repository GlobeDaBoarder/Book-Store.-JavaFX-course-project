package book_store;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Data
@Entity
public class Employee extends User{
    @Enumerated
    eEmpPositions pos;

    public Employee(String login, String password, eEmpPositions pos) {
        super(login, password);
        this.pos = pos;
    }

    public Employee() {

    }
}
