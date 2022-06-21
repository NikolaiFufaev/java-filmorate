package ru.yandex.practicum.filmorate.util;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AfterDateValidator implements ConstraintValidator<AfterDate, LocalDate> {

    @Override
    public boolean isValid(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate date = LocalDate.of(1895, 12, 28);
        return localDate.isAfter(date);
    }
}
