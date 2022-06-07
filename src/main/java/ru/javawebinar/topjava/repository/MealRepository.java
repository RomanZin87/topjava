package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    List<Meal> getAll();

    Meal create(Meal meal);

    void update(Meal meal);

    Meal get(int id);

    void delete(int id);
}