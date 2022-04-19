package webControllers;

import book_store.Person;
import book_store.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernateControllers.UserHibController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class UserWeb {
    /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    UserHibController userHibController = new UserHibController(entityManagerFactory);

    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String test() {
        return "ayo";
    }

    @RequestMapping(value = "user/validate", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String validateSuer(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        User user = userHibController.getUserByLoginData(login, password);
        return user.toString();
    }

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addUser(@RequestBody String request) {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        userHibController.createUser(new Person(data.getProperty("login"),
                data.getProperty("password"),
                data.getProperty("email"),
                data.getProperty("phone"),
                data.getProperty("name"),
                data.getProperty("surname")
        ));
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonSerializer());
        return (builder.create().toJson(individual, Individual.class));
    }*/
}
