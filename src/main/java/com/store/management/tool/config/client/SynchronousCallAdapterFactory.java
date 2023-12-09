package com.store.management.tool.config.client;

import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SynchronousCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(@NotNull final Type returnType,
                                 @NotNull final Annotation[] annotations,
                                 @NotNull final Retrofit retrofit) {
        if (getRawType(returnType) == Call.class) {
            return null;
        }

        if (getRawType(returnType) != Response.class) {
            return new SynchronousCallAdapter<>(returnType);
        }

        return new SynchronousCallAdapter<>(getParameterUpperBound(0, (ParameterizedType) returnType));
    }
}
