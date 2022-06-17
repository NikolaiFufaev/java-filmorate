package ru.yandex.practcum.filmorate.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
@Validated
public class FilmController {
    private Map<Integer, Film> filmMap = new HashMap<>();
    private LocalDate FILMS_BD = LocalDate.of(1895, Month.DECEMBER, 28);
    private static Integer id = 1;


    @GetMapping("/films")
    public List<Film> getAll() {
        return new ArrayList<>(filmMap.values());
    }

    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) {
        log.info("получен запрос на сохранение фильма");
        if (film.getId() == null) {
            film.setId(id++);
        }
        filmMap.put(film.getId(), film);
        return film;
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        if (!filmMap.containsKey(film.getId())) {
            throw new ValidationException("Фильма с таким id нет в базе");
        }
        log.info(film.toString());
        filmMap.put(film.getId(), film);
        return film;
    }

}
