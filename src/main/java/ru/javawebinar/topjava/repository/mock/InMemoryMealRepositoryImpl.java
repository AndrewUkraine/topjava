package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository("inMemoryMealRepositoryImpl")
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserID()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserID(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage!!
        return meal.getUserID()== userId ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal): null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = repository.get(id);
        if(meal != null && meal.getUserID() == userId){
            repository.remove(id);
            return true;
        }else return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal!=null|| meal.getUserID()==userId){
            return meal;
        }
       else return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {

        return repository.values().stream()
                .filter(meal -> meal.getUserID()==userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

