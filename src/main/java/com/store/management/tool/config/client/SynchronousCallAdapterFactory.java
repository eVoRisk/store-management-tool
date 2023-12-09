package com.store.management.tool.config.client;

import com.store.management.tool.exception.mapper.ExceptionMapper;
import com.store.management.tool.util.JsonUtil;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SynchronousCallAdapterFactory<E> extends CallAdapter.Factory {

    private final JsonUtil jsonUtil;
    private final ExceptionMapper<E> exceptionMapper;
    private final Class<E> failureClass;

    public SynchronousCallAdapterFactory(final JsonUtil jsonUtil,
                                         final ExceptionMapper<E> exceptionMapper,
                                         final Class<E> failureClass) {
        this.jsonUtil = jsonUtil;
        this.exceptionMapper = exceptionMapper;
        this.failureClass = failureClass;
    }

    @Override
    public CallAdapter<?, ?> get(@NotNull final Type returnType,
                                 @NotNull final Annotation[] annotations,
                                 @NotNull final Retrofit retrofit) {
        if (getRawType(returnType) == Call.class) {
            return null;
        }

        if (getRawType(returnType) != Response.class) {
            return new SynchronousCallAdapter<>(returnType, jsonUtil, exceptionMapper, failureClass);
        }

        return new SynchronousCallAdapter<>(getParameterUpperBound(0, (ParameterizedType) returnType),
                jsonUtil, exceptionMapper, failureClass);
    }
}
