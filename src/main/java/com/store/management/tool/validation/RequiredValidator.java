package com.store.management.tool.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Boolean.*;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class RequiredValidator implements ConstraintValidator<Required, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequiredValidator.class);
    private static final String APP_PACKAGE_NAME_PREFIX = "com.store";

    @Override
    public boolean isValid(final Object targetObject, final ConstraintValidatorContext context) {
        if (isCustomObject(targetObject)) {
            return TRUE.equals(getRequiredFields(targetObject)
                    .map(field -> withReflectionAccess(field, targetObject, fieldValue -> validateFieldValue(field, fieldValue, context)))
                    .reduce(Boolean::logicalAnd)
                    .orElse(null));
        }

        return validateValue(targetObject);
    }

    private boolean isCustomObject(final Object value) {
        return validateValue(value) && value.getClass().getPackageName().startsWith(APP_PACKAGE_NAME_PREFIX);
    }

    private boolean withReflectionAccess(final Field field, final Object targetObject, final Predicate<Object> fieldValueChecker) {
        boolean oldAccessLevel = field.canAccess(targetObject);
        field.setAccessible(true);

        try {
            return fieldValueChecker.test(field.get(targetObject));
        } catch (final IllegalAccessException e) {
            LOGGER.error("Failed to validate field \"{}\" (error={})", field.getName(), e.getMessage());

            return false;
        } finally {
            field.setAccessible(oldAccessLevel);
        }
    }

    private boolean validateFieldValue(final Field field, final Object fieldValue, final ConstraintValidatorContext context) {
        if (!validateValue(fieldValue)) {
            addNewConstraintViolationForField(field, context);

            return false;
        }

        return isValid(fieldValue, context);
    }

    private Stream<Field> getRequiredFields(final Object value) {
        return stream(value.getClass().getDeclaredFields())
                .filter(this::isMarkedAsRequired);
    }

    private boolean isMarkedAsRequired(final Field field) {
        return field.isAnnotationPresent(Required.class);
    }

    private void addNewConstraintViolationForField(final Field field, final ConstraintValidatorContext context) {
        Required required = field.getAnnotation(Required.class);

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(required.errorCode().name())
                .addPropertyNode(field.getName())
                .addConstraintViolation();
    }

    private boolean validateValue(final Object value) {
        if (value instanceof String stringValue) {
            return StringUtils.isNotBlank(stringValue);
        }

        return isNotEmpty(value);
    }
}
