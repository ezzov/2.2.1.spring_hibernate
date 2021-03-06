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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("First", "User", "first@mail.ru");
      user1.setCar(new Car("Lada", 15));
      User user2 = new User("Ekaterina", "Second", "velikaya@gmail.com");
      user2.setCar(new Car("Mazda", 5));

      userService.add(user1);
      userService.add(user2);

      User user3 = new User("Vasya", "Pechkin", "pekar@gmail.com");
      user3.setCar(new Car("Kamaz", 15));
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }

      User test = userService.getUserByCar("Kamaz", 15);
      System.out.println("Id = "+ test.getId());
      System.out.println("First Name = "+test.getFirstName());
      System.out.println("Last Name = "+test.getLastName());
      System.out.println("Email = "+test.getEmail());
      System.out.println(test.getCar());
      System.out.println();


      context.close();
   }
}
