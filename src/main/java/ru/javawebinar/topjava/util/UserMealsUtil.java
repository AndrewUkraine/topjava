package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {

        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
    }


    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal .getDateTime().toLocalDate(), //фильтруем все по дате. collect - это набираем набор действий, до термальной опирации
                        Collectors.summingInt(UserMeal::getCalories))); //получаем сумму каллорий по дням. Это термальная опирация! Все делать дальше ничего нельзя.


        return mealList.stream() //стрим где получим результат что нам нужен
                .filter(t -> TimeUtil.isBetween(t.getDateTime().toLocalTime(), startTime, endTime)) //фильтруем колекцию по вермени
                .map(userMeal  -> new UserMealWithExceed(userMeal .getDateTime(), userMeal .getDescription(), userMeal .getCalories(),
                        caloriesSumByDate.get(userMeal .getDateTime().toLocalDate()) > caloriesPerDay)) //берем сумму калорий в день из caloriesSumByDate и сравниваем с нормой >
                .collect(Collectors.toList()); //collect - получить данные не в виде потока, а в виде обычной коллекции, например, ArrayList или HashSet
    }
}


