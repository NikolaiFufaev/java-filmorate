package ru.yandex.practicum.filmorate.storage.user.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorageDao {
    List<User> findAll();

    User findById(Long id);

    User add(User user);

    User update(User user);

    boolean existsById(Long userId);
}
