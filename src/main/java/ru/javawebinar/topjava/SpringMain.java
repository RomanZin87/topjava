package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            //adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            List<User> all = adminUserController.getAll();
            all.forEach(System.out::println);

            MealRestController controller = appCtx.getBean(MealRestController.class);
            controller.getAll().forEach(System.out::println);
            controller.delete(2);
            controller.create(new Meal(LocalDateTime.of(2022, Month.DECEMBER, 6, 20,30), "Админ ужин", 500));
            Meal meal = new Meal(1, LocalDateTime.of(2022, Month.DECEMBER, 6, 10,30), "Обновленный завтрак", 500);

            controller.update(meal, 1);
            controller.getAll().forEach(System.out::println);
            MealService service = appCtx.getBean(MealService.class);
            System.out.println("туц");
            service.update(controller.get(1), 2);

        }
    }
}
