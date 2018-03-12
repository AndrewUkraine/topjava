package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null, "User_1", "User@mail.ru", "password", Role.ROLE_USER ),
            new User(null, "User_1", "User@mail.ru", "password", Role.ROLE_USER ),

    );



}

 super(id, name);
         this.email = email;
         this.password = password;
         this.caloriesPerDay = caloriesPerDay;
         this.enabled = enabled;
         this.roles = roles;