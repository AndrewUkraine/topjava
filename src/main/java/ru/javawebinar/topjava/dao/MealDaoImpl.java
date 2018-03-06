package ru.javawebinar.topjava.dao;
import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao {

    List<Meal> mealsDao = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 100),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 100),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 520),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 10, 0), "Завтрак", 100),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 20, 0), "Ужин", 100),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 2, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 2, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 2, 20, 0), "Ужин", 1000)

    );


    @Override
    public Meal create(Meal meal) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public Meal getById(int id) {
        return null;
    }

    @Override
    public List<Meal> getList() {
        return mealsDao.stream()
                .map(meal -> new Meal(meal.getDateTime(), meal.getDescription(), meal.getCalories()))
                .collect(Collectors.toList());
    }

}