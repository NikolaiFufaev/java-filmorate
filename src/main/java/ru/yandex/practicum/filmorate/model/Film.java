package ru.yandex.practicum.filmorate.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.util.AfterDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Film extends IdEntity{
    @NotBlank(message = "Название не может быть пустым")
    @NonNull
    private String name;
    @Length(message = "Длинна описания должна быть меньше 200 символов", max = 200)
    private String description;
    @AfterDate(message = "Фильм не может быть снят раньше 28-12-1895")
    private LocalDate releaseDate;
    @Positive(message = "Продолжительность не может быть отрицательной")
    private long duration;
    @NotNull
    private Mpa mpa;
    private List<Genre> genres = new ArrayList<>();
}
