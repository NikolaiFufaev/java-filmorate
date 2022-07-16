package ru.yandex.practicum.filmorate.storage.film.dao;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

public interface GenreStorageDao {
    List<Genre> findAll();

    Genre findById(Long genreId);

    List<Genre> add(Long filmId, List<Genre> genres);

    List<Genre> findAllById(Long genreId);

    boolean existsById(Long genreId);
}
