import book_store.EmpPositions;
import book_store.Employee;
import book_store.LegalEntity;
import hibernateControllers.UserHibController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
        UserHibController userHibController = new UserHibController(entityManagerFactory);

        /*Employee employee = new Employee("login", "pasword", EmpPositions.ADMIN);
        userHibController.createUser(employee);
        LegalEntity legalEntity = new LegalEntity("qq", "ww", "emaol", "ph", "name");
        userHibController.createUser(legalEntity);*/
    }
}
