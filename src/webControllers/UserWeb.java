package webControllers;

import WebSerializers.EmployeeSerializer;
import WebSerializers.LegalEntitySerilizer;
import WebSerializers.LocalDateGsonSerializer;
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

    /*
    @RequestMapping(value = "/users/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUserWeb(@PathVariable(name = "id") int id) {
        userHibController.removeUser(id);

        User user = userHibController.getUserById(id);
        if (user == null) return "Success";
        else return "Not deleted";
    }

    @RequestMapping(value = "/users/getAllUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllWebUsers() {
        List<User> users = userHibController.getAllUsers();

        GsonBuilder gson = new GsonBuilder();
        Type userList = new TypeToken<List<User>>() {
        }.getType();
        gson.registerTypeAdapter(userList, new UsersListGsonSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer())
                .registerTypeAdapter(Individual.class, new IndividualGsonSerializer());
        Gson parser = gson.create();
        return parser.toJson(users);
    }

    @RequestMapping(value = "/users/updateUser", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateWebUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        Individual individual = null;
        LegalPerson legalPerson = null;
        if (data.getProperty("userType").equals("I")) {
            individual = (Individual) userHibController.getUserById(Integer.parseInt(data.getProperty("id")));

            individual.setName(data.getProperty("name"));
            individual.setEmail(data.getProperty("email"));
            userHibController.updateUser(individual);
            return "Updated";
        } else if (data.getProperty("userType").equals("L")) {
            //finish specific
            return "Updated";
        } else {
            return "No such user type, unable to update";
        }
    }

     */
}
