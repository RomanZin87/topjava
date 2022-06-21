package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND_MEAL_ID = 100;

    public static final Meal userMeal1 = new Meal(START_SEQ + 3,
            LocalDateTime.of(2022, Month.JUNE, 17, 10, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(START_SEQ + 4,
            LocalDateTime.of(2022, Month.JUNE, 17, 13, 0), "Обед", 1000);
    public static final Meal userMeal3 = new Meal(START_SEQ + 5,
            LocalDateTime.of(2022, Month.JUNE, 17, 20, 0), "Ужин", 500);
    public static final Meal userMeal4 = new Meal(START_SEQ + 6,
            LocalDateTime.of(2022, Month.JUNE, 18, 0, 0), "Еда на граничное значение", 100);
    public static final Meal userMeal5 = new Meal(START_SEQ + 7,
            LocalDateTime.of(2022, Month.JUNE, 18, 10, 30), "Завтрак", 1000);
    public static final Meal userMeal6 = new Meal(START_SEQ + 8,
            LocalDateTime.of(2022, Month.JUNE, 18, 14, 0), "Обед", 500);
    public static final Meal userMeal7 = new Meal(START_SEQ + 9,
            LocalDateTime.of(2022, Month.JUNE, 18, 19, 30), "Ужин", 410);
    public static final Meal adminMeal1 = new Meal(START_SEQ + 10,
            LocalDateTime.of(2022, Month.JUNE, 1, 14, 0), "Админ Ланч", 510);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 11,
            LocalDateTime.of(2022, Month.JUNE, 1, 21, 0), "Админ Ужин", 1500);

    public static final List<Meal> mealList = Arrays.asList(
            userMeal7, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JANUARY, 1, 12, 0), "Новая Еда", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDateTime(LocalDateTime.of(2020, Month.MARCH, 8, 10, 0));
        updated.setDescription("Обновленная еда");
        updated.setCalories(700);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
