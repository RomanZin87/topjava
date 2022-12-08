package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        int id = adminBreakfast.getId();
        Meal actual = mealService.get(id, ADMIN_ID);
        assertEquals(adminBreakfast, actual);
    }

    @Test
    public void getNotYours() {
        int id = adminBreakfast.getId();
        assertThrows(NotFoundException.class, () -> mealService.get(id, USER_ID));
    }

    @Test
    public void delete() {
        int id = userBreakfast.getId();
        mealService.delete(id, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(id, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = mealService.getBetweenInclusive(LocalDate.of(2022, Month.DECEMBER, 8), LocalDate.of(2022, Month.DECEMBER, 8), ADMIN_ID);
        assertEquals(List.of(adminBreakfast), actual);
    }

    @Test
    public void getAll() {
        List<Meal> actual = mealService.getAll(USER_ID);
        assertEquals(List.of(userLunch, userBreakfast), actual);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        mealService.update(updated, USER_ID);
        assertEquals(mealService.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void updateNotYours() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> mealService.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), USER_ID);
        int newId = created.getId();
        Meal expected = getNew();
        expected.setId(newId);
        assertEquals(expected, created);
        assertEquals(mealService.get(newId, USER_ID), created);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal mealToCreate = getNew();
        mealToCreate.setDateTime(userBreakfast.getDateTime());
        assertThrows(DataAccessException.class, () -> mealService.create(mealToCreate, USER_ID));
    }
}