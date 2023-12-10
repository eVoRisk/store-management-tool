package com.store.management.tool.observer.logging;

import com.store.management.tool.exception.StoreManagementToolException;
import com.store.management.tool.observer.ProcessingObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.store.management.tool.observer.logging.StructuredArgumentKey.ERROR_CODE;
import static java.util.Arrays.stream;
import static java.util.stream.Stream.concat;
import static net.logstash.logback.argument.StructuredArguments.kv;

public interface LoggerObserver<T, V> extends ProcessingObserver<T, V> {

    Logger LOGGER = LoggerFactory.getLogger(LoggerObserver.class);

    @Override
    default void process(final T request, final V response) {
        LOGGER.info(getSuccessMessage(), concatenateArguments(request, response));
    }

    @Override
    default void processException(final T request, final StoreManagementToolException exception) {
        LOGGER.error(getFailureMessage(), concatenateArgumentsAndException(request, exception));
    }

    private Object[] concatenateArguments(final T request, final V response) {
        var requestArguments = stream(getStructuredArgumentsRequest(request));
        var responseArguments = stream(getStructuredArgumentsResponse(response));

        return concat(requestArguments, responseArguments).toArray();
    }

    private Object[] concatenateArgumentsAndException(final T request, final StoreManagementToolException exception) {
        var requestArguments = stream(getStructuredArgumentsRequest(request));
        var exceptionArguments = stream(getStructuredArguments(exception));

        return concat(requestArguments, exceptionArguments).toArray();
    }

    private Object[] getStructuredArguments(final StoreManagementToolException exception) {
        return new Object[]{
                kv(ERROR_CODE.getValue(), exception.getErrorCode())
        };
    }

    String getSuccessMessage();

    String getFailureMessage();

    Object[] getStructuredArgumentsRequest(T request);

    Object[] getStructuredArgumentsResponse(V response);
}
