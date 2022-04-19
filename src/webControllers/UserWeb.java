package webControllers;

import WebSerializers.EmployeeSerializer;
import WebSerializers.LegalEntitySerilizer;
import WebSerializers.LocalDateGsonSerializer;
import WebSerializers.UsersListGsonSerializer;
import book_store.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hibernateControllers.UserHibController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class UserWeb {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String test() {
        return "ayo";
    }

    @RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String validateWebUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        User user = userHibController.getUserByLoginData(data.getProperty("login"), data.getProperty("password"));

        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer())
                .registerTypeAdapter(Employee.class, new EmployeeSerializer());
        if (user.getClass() == Employee.class) {
            Employee employee = (Employee) user;
            return (builder.create().toJson(employee));
        } else if (user.getClass() == Person.class){
            Person person = (Person) user;
            return (builder.create().toJson(person, Person.class));
        }else{
            LegalEntity legalEntity = (LegalEntity) user;
            return (builder.create().toJson(legalEntity, LegalEntity.class));
        }
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addWebUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        Person person = null;
        Employee employee = null;
        LegalEntity legalEntity = null;
        if (data.getProperty("DTYPE").equals("Employee")) {
            userHibController.createUser(new Employee(data.getProperty("login"), data.getProperty("password"),
                    eEmpPositions.valueOf(data.getProperty("pos"))));
            employee = (Employee) userHibController.getUserByLoginData(data.getProperty("login"), data.getProperty("password"));
        } else if (data.getProperty("DTYPE").equals("Person")) {
            userHibController.createUser((new Person(data.getProperty("login"), data.getProperty("password"),
                    data.getProperty("email"), data.getProperty("phone"), data.getProperty("name"),
                    data.getProperty("surname"))));
            person = (Person) userHibController.getUserByLoginData(data.getProperty("login"), data.getProperty("password"));
        } else if (data.getProperty("DTYPE").equals("LegalEntity")){
            userHibController.createUser(new Person(data.getProperty("login"), data.getProperty("password"),
                    data.getProperty("email"), data.getProperty("phone"), data.getProperty("name"),
                    data.getProperty("companyName")));
            legalEntity = (LegalEntity) userHibController.getUserByLoginData(data.getProperty("login"), data.getProperty("password"));
        }else{
            return "no such user type";
        }

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer());
        if (employee != null) {

            return (builder.create().toJson(employee, Employee.class));
        }
        if ( person!= null) {
            return (builder.create().toJson(person, Person.class));
        }
        if ( legalEntity!= null) {
            return (builder.create().toJson(legalEntity, LegalEntity.class));
        }
        return "Fail";
    }


    @RequestMapping(value = "/user/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserWeb(@PathVariable(name = "id") int id) {
        userHibController.removeUser(id);

        User user = userHibController.getUserById(id);
        if (user == null) return "Success";
        else return "Not deleted";
    }


    @RequestMapping(value = "/user/getAllUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllWebUsers() {
        List<User> users = userHibController.getAllUsers();

        GsonBuilder gson = new GsonBuilder();
        Type userList = new TypeToken<List<User>>() {
        }.getType();
        gson.registerTypeAdapter(userList, new UsersListGsonSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer())
                .registerTypeAdapter(Employee.class, new EmployeeSerializer());
        Gson parser = gson.create();
        return parser.toJson(users);
    }

    @RequestMapping(value = "/user/updateUser", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateWebUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        Person person = null;
        Employee employee = null;
        LegalEntity legalEntity = null;
        if (data.getProperty("DTYPE").equals("Employee")) {
            employee = (Employee) userHibController.getUserById(Integer.parseInt(data.getProperty("id")));
            employee.setLogin(data.getProperty("login"));
            employee.setPassword(data.getProperty("password"));
            employee.setPos(eEmpPositions.valueOf(data.getProperty("pos")));
            userHibController.updateUser(employee);
            return "Updated";
        }
        if (data.getProperty("DTYPE").equals("Person")) {
            person = (Person) userHibController.getUserById(Integer.parseInt(data.getProperty("id")));
            person.setLogin(data.getProperty("login"));
            person.setPassword(data.getProperty("password"));
            person.setEmail(data.getProperty("email"));
            person.setPhone(data.getProperty("phone"));
            person.setName(data.getProperty("name"));
            person.setSurname(data.getProperty("surname"));
            userHibController.updateUser(person);
            return "Updated";
        }
        if (data.getProperty("DTYPE").equals("LegalEntity")){
            legalEntity = (LegalEntity) userHibController.getUserById(Integer.parseInt(data.getProperty("id")));
            legalEntity.setLogin(data.getProperty("login"));
            legalEntity.setPassword(data.getProperty("password"));
            legalEntity.setEmail(data.getProperty("email"));
            legalEntity.setPhone(data.getProperty("phone"));
            legalEntity.setCompanyName(data.getProperty("companyName"));
            userHibController.updateUser(legalEntity);
            return "Updated";
        }

        return "Fail";
    }
}
