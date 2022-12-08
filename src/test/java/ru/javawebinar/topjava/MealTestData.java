package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {

    public static final Meal userBreakfast = new Meal(100003, LocalDateTime.of(2022, Month.DECEMBER, 8, 10,0), "Завтрак", 500);
    public static final Meal userLunch = new Meal(100004, LocalDateTime.of(2022, Month.DECEMBER, 8, 12,0), "Обед", 1000);
    public static final Meal adminBreakfast = new Meal(100005, LocalDateTime.of(2022, Month.DECEMBER, 8, 10,30), "Завтрак", 700);
    public static final Meal adminLunch = new Meal(100006, LocalDateTime.of(2022, Month.DECEMBER, 10, 12,30), "Обед", 900);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "Новая еда", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userBreakfast);
        updated.setDescription("Обновленная еда");
        updated.setCalories(777);
        return updated;
    }
}
