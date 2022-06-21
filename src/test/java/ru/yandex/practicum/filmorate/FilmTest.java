package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FilmTest {
    private static Validator validator;
    private static Film film = new Film();

    @BeforeAll
    public static void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    private void updateFilm() {
        film.setName("Фильм");
        film.setDescription("Описание");
        film.setReleaseDate(LocalDateTime.now().toLocalDate());
        film.setDuration(1);
    }

    @Test
    void filmNameIsNull() {
        film.setName("");
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals("Название не может быть пустым", violations.iterator().next().getMessage());
    }

    @Test
    void filmDescriptionLengthMoreThan200Characters() {
        film.setDescription("a".repeat(201));
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals("Длинна описания должна быть меньше 200 символов", violations.iterator().next().getMessage());
    }

    @Test
    void theReleaseDateCannotBeEarlier28_12_1895() {
        film.setReleaseDate(LocalDate.of(1785, 12, 12));
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals("Фильм не может быть снят раньше 28-12-1895", violations.iterator().next().getMessage());
    }

    @Test
    void durationIsPositive() {
        film.setDuration(-1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertEquals("Продолжительность не может быть отрицательной", violations.iterator().next().getMessage());
    }

}
