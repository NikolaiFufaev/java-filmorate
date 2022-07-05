package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage filmStorage;
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmStorage filmStorage, FilmService filmService) {
        this.filmService = filmService;
        this.filmStorage = filmStorage;
    }

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен GET-запрос к эндпоинту /films");
        return filmStorage.getFilms();
    }

    @GetMapping("/{Id}")
    public Film getFilmById(@PathVariable long Id) {
        return filmStorage.getFilmById(Id);
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Создан объект '{}'", film);
        return filmStorage.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Обновлен объект '{}'", film);
        return filmStorage.update(film);
    }

    @PutMapping("/{userId}/like/{filmId}")
    public void addLike(@PathVariable int userId, @PathVariable int filmId) {
        filmService.addLike(userId, filmId);
    }

    @DeleteMapping("/{userId}/like/{filmId}")
    public void removeLike(@PathVariable int userId, @PathVariable int filmId) {
        filmService.removeLike(userId, filmId);
    }

    @GetMapping("/popular")
    public List<Film> getListPopularFilms(@RequestParam(required = false) String count) {
        List<Film> filmList = null;
        if (count == null) {
            System.out.println("Before filmList");
            filmList = filmService.getListPopularFilms(10);
            System.out.println("FilmList " + filmList);
        } else {
            filmList = filmService.getListPopularFilms(Integer.parseInt(count));
        }
        return filmList;
    }

    @DeleteMapping("/{id}")
    public Film deleteFilm(@PathVariable("id") long id){
        return filmStorage.delete(id);
    }
}