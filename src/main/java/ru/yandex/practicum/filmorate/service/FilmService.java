package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.dao.FilmStorageDao;
import ru.yandex.practicum.filmorate.storage.film.dao.GenreStorageDao;
import ru.yandex.practicum.filmorate.storage.film.dao.LikeStorageDao;
import ru.yandex.practicum.filmorate.storage.user.dao.UserStorageDao;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorageDao filmStorage;
    private final UserStorageDao userStorage;
    private final LikeStorageDao likeStorage;
    private final GenreStorageDao genreStorage;

    public List<Film> findAll() {
        log.info("Получение списка всех фильмов");
        return filmStorage.findAll();
    }

    public Film add(Film film) {
        filmStorage.add(film);
        genreStorage.add(film.getId(), film.getGenres());
        log.info("Добавление нового фильма c id {}", film.getId());

        return findFilmById(film.getId());
    }

    public Film update(Film film) {
        if (!filmStorage.existsById(film.getId())) {
            log.warn("Фильм с id {} не найден", film.getId());
            throw new ObjectNotFoundException("Фильм не найден");
        }
        filmStorage.update(film);
        genreStorage.add(film.getId(), film.getGenres());
        log.info("Обновление фильма с id {}", film.getId());

        return findFilmById(film.getId());
    }

    public Film findFilmById(Long filmId) {
        if (!filmStorage.existsById(filmId)) {
            log.warn("Фильм с id {} не найден", filmId);
            throw new ObjectNotFoundException("Фильм не найден");
        }
        Film foundFilm = filmStorage.findById(filmId);
        foundFilm.setGenres(genreStorage.findAllById(filmId));
        log.info("Получение фильма с id {}", filmId);
        return foundFilm;
    }

    public List<Film> getFilms(int count) {
        log.info("Получение {} фильмов", count);
        return filmStorage.findFilms(count);
    }

    public void addLike(Long filmId, Long userId) {
        if (!(filmStorage.existsById(filmId) && userStorage.existsById(userId))) {
            throw new ObjectNotFoundException("Вызов несуществующего объекта");
        }
        log.info("Пользователь {} поставил лайк фильму {}", userId, filmId);
        likeStorage.addLike(filmId, userId);
    }

    public void deleteLike(Long filmId, Long userId) {
        if (!(filmStorage.existsById(filmId) && userStorage.existsById(userId))) {
            throw new ObjectNotFoundException("Вызов несуществующего объекта");
        }
        log.info("Пользователь {} удалил лайк у фильма {}", userId, filmId);
        likeStorage.deleteLike(filmId, userId);
    }
}
