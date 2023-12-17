package com.store.management.tool.validation;

import com.store.management.tool.exception.ErrorCode;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.store.management.tool.exception.ErrorCode.INVALID_INPUT;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredValidator.class)
public @interface Required {

    String message() default "This field is required";

    ErrorCode errorCode() default INVALID_INPUT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
