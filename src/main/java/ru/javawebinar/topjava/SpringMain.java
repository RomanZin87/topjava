package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            adminUserController.create(new User(null, "userName", "email2@mail.ru", "password", Role.USER));
            adminUserController.create(new User(null, "userName", "email3@mail.ru", "password", Role.USER));
            adminUserController.getAll().forEach(System.out::println);
            System.out.println("------------------------------------------------");

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("------------------------------------------------");
            mealRestController.create(new Meal(LocalDateTime.of(2022, Month.JUNE, 11, 10, 0), "Новая еда", 500));
            System.out.println("------------------------------------------------");
            mealRestController.delete(3);
            System.out.println("------------------------------------------------");
            System.out.println(mealRestController.get(4));
            System.out.println("------------------------------------------------");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("------------------------------------------------");
            mealRestController.getAllBetween(LocalDate.of(2020, Month.JANUARY, 31),
                    LocalDate.of(2020, Month.FEBRUARY, 1),
                    LocalTime.of(10,0),LocalTime.of(23,0))
                    .forEach(System.out::println);
        }
    }
}
