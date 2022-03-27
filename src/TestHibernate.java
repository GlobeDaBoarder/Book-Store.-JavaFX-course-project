import hibernateControllers.UserHibController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
        UserHibController userHibController = new UserHibController(entityManagerFactory);

        /*Employee employee = new Employee("a", "a", EmpPositions.ADMIN);
        userHibController.createUser(employee);*/

    }
}
