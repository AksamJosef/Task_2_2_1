package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car[] carArray = {
              new Car("Nissan", 34),
              new Car("Toyota", 50),
              new Car("Honda", 32),
              new Car("Nissan", 32)
      };


      for (int i = 1; i < 5; i++) {
         String name = "User" + i;
         String lastName = "Lastname" + i;
         String email = "user" + i + "@mail.ru";
         User user = new User(name, lastName, email);
         user.setCar(carArray[i-1]);
         userService.add(user);
      }

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel() + ", car series = "
                 + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("Result of method getUserByCar");
      userService.getUserByCar("Nissan", 34).forEach(System.out::println);

      context.close();
   }
}
