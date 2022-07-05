package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    public List<Film> getFilms();

    public Film getFilmById(long id);

    public Film create(Film film);

    public Film update (Film film);

    public Film delete(long id);
}