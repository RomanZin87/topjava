package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAO {
    private static final AtomicInteger id = new AtomicInteger(0);
    private static final Map<Integer, Meal> db = new ConcurrentHashMap<>();

    static {
        MealsUtil.meals.forEach(meal -> db.put(id.incrementAndGet(), meal));
    }

    public Map<Integer, Meal> getAll() {
        return db;
    }

    public Meal getById(int id) {
        return db.get(id);
    }

    public void save(Meal meal) {
        db.put(id.incrementAndGet(), meal);
    }

    public void update(int id, Meal meal) {
        db.put(id, meal);
    }

    public void delete(int id) {
        db.remove(id);
    }
}
