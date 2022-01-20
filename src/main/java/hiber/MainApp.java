package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 1);
      Car car2 = new Car("BMW", 6);
      Car car3 = new Car("Kia", 5);
      Car car4 = new Car("Reno", 8);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

//      System.out.println(car1);
//      System.out.println(car2);
//      System.out.println(car3);
//      System.out.println(car4);

      user1.setCar(car3);
      user2.setCar(car4);
      user3.setCar(car1);
      user4.setCar(car2);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println("\n\n");
      List<User> users = userService.listUsers();
      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car = "+user.getCar());
         System.out.println(user);
         System.out.println();
      }
      System.out.println("\n\n");

      System.out.println(userService.getUserByCar("Reno", 8));

      context.close();
   }
}