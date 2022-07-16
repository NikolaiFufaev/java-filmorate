package ru.yandex.practicum.filmorate.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends IdEntity{
    @Email(message = "Эмейл написан не в формате электронного адресса")
    private String email;

    @NotBlank(message = "Логин не должен быть пустым")
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы и быть пустым")
    private String login;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения не может быть из будущего")
    private LocalDate birthday;


}
