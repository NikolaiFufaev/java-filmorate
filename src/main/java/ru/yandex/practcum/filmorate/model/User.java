package ru.yandex.practcum.filmorate.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    private Integer id;
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

}
