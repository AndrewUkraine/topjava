package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
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
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        assertMatch(service.get(MEAL1_ID, USER_ID), MEAL1);

    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL1.getId(), USER_ID);
        assertMatch(service.getAll(USER_ID),MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> filtered = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID);
        assertMatch(filtered, MEAL1, MEAL2, MEAL3);    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> filtered = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), USER_ID);
        assertMatch(filtered, MEAL5, MEAL6);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);


    }

    @Test(expected = NotFoundException.class)
    public void updateNotYours() {
        service.update(MEAL2, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotYours() {
        service.get(MEAL1.getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotYours() {
        service.delete(MEAL1.getId(), ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Lunch");
        updated.setCalories(1000);
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL1.getId(), USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal meal = new Meal(null, LocalDateTime.of(2016, Month.MAY, 11, 10, 30), "descpirtion", 1000);
        Meal create = service.create(meal, USER_ID);
        assertMatch(service.getAll(USER_ID), create, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

}