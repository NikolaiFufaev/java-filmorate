package ru.yandex.practcum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {
    @EqualsAndHashCode.Include
    private Integer id;
    @Email(message = "Эмейл написан не в формате электронного адресса")
    @NotBlank(message = "Эмейл не может быть пустым")
    @EqualsAndHashCode.Include
    private String email;
    @NotEmpty(message = "Логин не может быть пустым")
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы")
    private String login;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения не может быть из будущего")
    private LocalDate birthday;

}
