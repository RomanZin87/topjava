package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    public List<Meal> getAll();

    public void save(Meal meal);

    public Meal get(int id);

    public void delete(int id);
}
