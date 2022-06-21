package ru.yandex.practicum.filmorate.storage.film;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Getter
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final HashMap<Long, Film> films = new HashMap<>();
    private long id = 1;

    @Override
    public List<Film> getFilms() {

        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(long id) {
        Film film;
        if (films.containsKey(id)) {
            film = films.get(id);
        } else {
            throw new ObjectNotFoundException(
                    String.format("Фильма с id \"%s\"не существует.", id));
        }
        return film;
    }

    @Override
    public Film create(Film film) {
        if (film.getId() == null) {
            updateId(film);
        } else {
            throw new ValidationException("Фильм должен быть передан без id");
        }
        films.put(film.getId(), film);

        return film;
    }

    @Override
    public Film update(Film film) {
        if (film.getId() == null) {
            throw new ValidationException("Введите id фильма, которого необходимо обновить");
        }
        if (!films.containsKey(film.getId())) {
            throw new ObjectNotFoundException("Указанного фильма не существует");
        }
        films.put(film.getId(), film);
        return film;
    }

    private void updateId(Film film) {
        film.setId(id++);
    }
}