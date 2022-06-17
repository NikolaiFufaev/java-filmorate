package ru.yandex.practcum.filmorate.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    @EqualsAndHashCode.Include
    private Integer id;
    @NotBlank(message = "Название не может быть пустым")
    @NonNull
    private String name;
    @Length(message = "Длинна описания должна быть меньше 200 символов", max = 200)
    private String description;
    @AfterDate(message = "Фильм не может быть снят раньше 28-12-1895")
    private LocalDate releaseDate;
    @Positive(message = "Продолжительность не может быть отрицательной")
    private int duration;
}
