package com.store.management.tool.observer;

import com.store.management.tool.exception.StoreManagementToolException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.store.management.tool.exception.ErrorCode.INTERNAL_ERROR;

public final class Observability {

    private Observability() {

    }

    public static <T, V> V executeWithObserver(final Function<T, V> code,
                                               final Supplier<ProcessingObserver<T, V>> logger,
                                               final T request) {
        return execute(
                () -> code.apply(request),
                response -> logger.get().process(request, response),
                exception -> logger.get().processException(request, exception)
        );
    }

    private static <T> T execute(final Supplier<T> code,
                                 final Consumer<T> observer,
                                 final Consumer<StoreManagementToolException> exceptionObserver) {
        T res = null;
        StoreManagementToolException error = null;

        try {
            res = code.get();
        } catch (final StoreManagementToolException e) {
            error = e;
        } catch (final Exception e) {
            error = new StoreManagementToolException(INTERNAL_ERROR, e);
        }

        if (error == null) {
            observer.accept(res);
        } else {
            exceptionObserver.accept(error);
            throw error;
        }

        return res;
    }
}
