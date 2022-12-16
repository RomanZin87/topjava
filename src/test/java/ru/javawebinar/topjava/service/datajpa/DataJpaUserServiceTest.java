package ru.javawebinar.topjava.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getWithMeals() {
        User actual = service.getWithMeals(USER_ID);
        USER_MATCHER.assertMatch(actual, user);
        MEAL_MATCHER.assertMatch(actual.getMeals(), List.of(meal1, meal2, meal3, meal4, meal5, meal6, meal7));
    }

    @Test
    public void getWithMealsNotFound() {
        Assert.assertThrows(NotFoundException.class, ()-> service.getWithMeals(UserTestData.NOT_FOUND));
    }
}
