package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"jdbc","postgres"})
public class MealServiceJdbcTest extends MealServiceTest {
}