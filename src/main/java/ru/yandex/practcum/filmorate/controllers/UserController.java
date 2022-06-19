package ru.yandex.practcum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practcum.filmorate.model.User;
import ru.yandex.practcum.filmorate.exceptions.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final Map<Integer, User> userMap = new HashMap<>();
    private static Integer id = 1;

    @GetMapping
    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        if (user.getId() == null) {
            user.setId(id++);
        }
        if (user.getName().isEmpty()) {
            log.info("Пустое имя было замененно на логин");
            user.setName(user.getLogin());
        }
        userMap.put(user.getId(), user);
        log.info(user.toString());
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        if (!userMap.containsKey(user.getId())) {
            throw new ValidationException("Пользователя с таким id нет в базе");
        }
        userMap.put(user.getId(), user);
        log.info(user.toString());
        return user;
    }


}
