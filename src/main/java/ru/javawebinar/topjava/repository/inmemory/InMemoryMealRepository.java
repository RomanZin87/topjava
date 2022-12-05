package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;

@Repository
public class InMemoryMealRepository extends InMemoryBaseRepository<Meal> implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        return super.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete meal with id {}", id);
        return super.delete(id);
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get meal with id {}", id);
        return super.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll()");
        return super.getAll();
    }
}

