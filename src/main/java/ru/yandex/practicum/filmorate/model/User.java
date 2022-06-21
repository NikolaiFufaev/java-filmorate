package ru.yandex.practicum.filmorate.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    @Email(message = "Эмейл написан не в формате электронного адресса")
    @NotBlank(message = "Эмейл не может быть пустым")
    @EqualsAndHashCode.Include
    private String email;
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы и быть пустым")
    private String login;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения не может быть из будущего")
    private LocalDate birthday;
    private Set<Integer> friends = new HashSet<>();
    private Set<Integer> favoriteFilms = new HashSet<>();

}
