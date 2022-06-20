package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal createdMeal = service.create(getNew(), USER_ID);
        Integer createdMealId = createdMeal.getId();
        Meal newMeal = getNew();
        newMeal.setId(createdMealId);
        assertMatch(createdMeal, newMeal);
        assertMatch(service.get(createdMealId, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        service.delete(meal1.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(meal1.getId(), USER_ID));
    }

    @Test
    public void deleteAnotherUsersMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(meal1.getId(), ADMIN_ID));
    }

    @Test
    public void deleteNotFoundMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void duplicateDatetimeMeal() {
        assertThrows(DuplicateKeyException.class,
                () -> service.create(new Meal(null, meal1.getDateTime(), "Новая еда в то же время", 500), USER_ID));
    }

    @Test
    public void get() {
        Meal receivedMeal = service.get(meal2.getId(), USER_ID);
        assertMatch(receivedMeal, meal2);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void getAnotherUsersMeal() {
        assertThrows(NotFoundException.class, () -> service.get(meal3.getId(), ADMIN_ID));
    }

    @Test
    public void update() {
        Meal updatedMeal = getUpdated();
        service.update(getUpdated(), USER_ID);
        assertMatch(service.get(meal1.getId(), USER_ID), updatedMeal);
    }

    @Test
    public void updateAnotherUsersMeal() {
        assertThrows(NotFoundException.class, () -> service.update(meal4, ADMIN_ID));
        assertMatch(service.get(meal4.getId(), USER_ID), meal4);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), mealList);
    }

    @Test
    public void getBetweenInclusive() {
        assertMatch(service.getBetweenInclusive(
                LocalDate.of(2022, Month.JUNE, 1),
                LocalDate.of(2022, Month.JUNE, 1),
                ADMIN_ID), meal9, meal8);
    }


}