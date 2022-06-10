package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.repository.MealRepository;

public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }
}