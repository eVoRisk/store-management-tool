package com.store.management.tool.controller;

import com.store.management.tool.exception.ErrorCode;
import com.store.management.tool.exception.FailureResponse;
import com.store.management.tool.exception.StoreManagementToolException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;
import java.util.StringJoiner;

import static com.store.management.tool.exception.ErrorCode.*;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler({StoreManagementToolException.class})
    private ResponseEntity<Object> handleStoreManagementToolException(final StoreManagementToolException e) {
        return new ResponseEntity<>(createFailureResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<Object> handleException() {
        return new ResponseEntity<>(createFailureResponse(INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull final MethodArgumentNotValidException e,
                                                                  @NotNull final HttpHeaders headers,
                                                                  @NotNull final HttpStatusCode status,
                                                                  @NotNull final WebRequest request) {
        var bindingResult = e.getBindingResult();

        if (bindingResult.getAllErrors().size() == 1) {
            ErrorCode code = ofNullable(bindingResult.getFieldError())
                    .flatMap(this::findErrorCodeFromFieldError)
                    .orElse(MALFORMED_INPUT);

            return new ResponseEntity<>(createFailureResponse(code), HttpStatus.BAD_REQUEST);
        }

        StringJoiner joiner = new StringJoiner(", ");
        bindingResult.getFieldErrors().forEach(fieldError -> joiner.add(fieldError.getField()));

        return new ResponseEntity<>(createFailureResponse(joiner.toString()), HttpStatus.BAD_REQUEST);
    }

    private FailureResponse createFailureResponse(final StoreManagementToolException e) {
        return new FailureResponse(e.getErrorCode(), e.getMessage());
    }

    private FailureResponse createFailureResponse(ErrorCode code) {
        return new FailureResponse(code, code.getMessage());
    }

    private FailureResponse createFailureResponse(String fields) {
        return new FailureResponse(MISSING_MULTIPLE_FIELDS, String.format(MISSING_MULTIPLE_FIELDS.getMessage(), fields));
    }

    private Optional<ErrorCode> findErrorCodeFromFieldError(final FieldError fieldError) {
        return ofNullable(fieldError.getDefaultMessage())
                .flatMap(ErrorCode::findValueByName)
                .or(() -> findErrorCodeFromValidationErrorArguments(fieldError.getArguments()));
    }

    private Optional<ErrorCode> findErrorCodeFromValidationErrorArguments(final Object[] args) {
        return stream(args)
                .filter(ErrorCode.class::isInstance)
                .map(ErrorCode.class::cast)
                .findFirst();
    }
}
