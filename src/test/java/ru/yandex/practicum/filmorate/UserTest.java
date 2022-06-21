package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {
    private static Validator validator;
    private static User user = new User();

    @BeforeAll
    public static void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    private void updateUser() {
        user.setEmail("friend@common.ru");
        user.setName("имя");
        user.setLogin("логин");
        user.setBirthday(LocalDate.of(2000, 8, 20));
    }

    @Test
    void theEmailMustContainTheSymbolDog() {
        user.setEmail("friendcommon.ru");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("Эмейл написан не в формате электронного адресса", violations.iterator().next().getMessage());
    }

    @Test
    void emailCannotBeEmpty() {
        user.setEmail("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("Эмейл не может быть пустым", violations.iterator().next().getMessage());
    }

    @Test
    void loginCannotBeEmpty() {
        user.setLogin("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("Логин не должен содержать пробелы и быть пустым", violations.iterator().next().getMessage());
    }

    @Test
    void loginMustNotContainSpaces() {
        user.setLogin("имя имя");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("Логин не должен содержать пробелы и быть пустым", violations.iterator().next().getMessage());
    }

    @Test
    void dateOfBirthCannotBeFromTheFuture() {
        user.setBirthday(LocalDate.of(2222, 10, 10));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals("Дата рождения не может быть из будущего", violations.iterator().next().getMessage());
    }

}