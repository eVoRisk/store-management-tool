package com.store.management.tool.config.client;

import com.store.management.tool.exception.ErrorCode;
import com.store.management.tool.exception.StoreManagementToolException;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.lang.reflect.Type;
import java.util.Optional;

public class SynchronousCallAdapter<R> implements CallAdapter<R, Object> {

    private final Type responseType;

    SynchronousCallAdapter(final Type responseType) {
        this.responseType = responseType;
    }

    @NotNull
    @Override
    public Type responseType() {
        return responseType;
    }

    @NotNull
    @Override
    public Response<R> adapt(@NotNull final Call<R> call) {
        Response<R> response;

        try {
            response = call.execute();
        } catch (final Exception e) {
            throw new StoreManagementToolException(e);
        }

        return Optional.of(response)
                .filter(Response::isSuccessful)
                .filter(r -> r.body() != null)
                .orElseThrow(() -> new StoreManagementToolException(ErrorCode.INTERNAL_ERROR));
    }
}
