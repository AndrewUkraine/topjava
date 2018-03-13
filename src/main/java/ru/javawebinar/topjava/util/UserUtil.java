package ru.javawebinar.topjava.util;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import java.util.Arrays;
import java.util.List;

public class UserUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null, "User_1", "User1@mail.ru", "password1", Role.ROLE_USER ),
            new User(null, "User_2", "User2@mail.ru", "password2", Role.ROLE_USER ),
            new User(null, "User_3", "User3@mail.ru", "password3", Role.ROLE_USER )

    );



}

