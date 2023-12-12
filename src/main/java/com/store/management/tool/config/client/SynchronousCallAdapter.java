package com.store.management.tool.config.client;

import com.store.management.tool.exception.mapper.ExceptionMapper;
import com.store.management.tool.exception.StoreManagementToolException;
import com.store.management.tool.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.lang.reflect.Type;

import static com.store.management.tool.exception.ErrorCode.INTERNAL_ERROR;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class SynchronousCallAdapter<R, E> implements CallAdapter<R, Object> {

    private final Type responseType;
    private final JsonUtil jsonUtil;
    private final ExceptionMapper<E> exceptionMapper;
    private final Class<E> failureClass;

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

        return of(response)
                .filter(Response::isSuccessful)
                .filter(r -> r.body() != null)
                .orElseThrow(() -> mapToException(response));
    }

    private StoreManagementToolException mapToException(final Response<?> response) {
        try(final ResponseBody errorBody = response.errorBody()) {
            return ofNullable(errorBody)
                    .map(this::getResponseBodyString)
                    .map(stringErrorBody -> jsonUtil.fromJson(stringErrorBody, failureClass))
                    .map(exceptionMapper::map)
                    .orElse(new StoreManagementToolException(INTERNAL_ERROR));
        }
    }

    private String getResponseBodyString(final ResponseBody responseBody) {
        try {
            return responseBody.string();
        } catch (final Exception e) {
            throw new StoreManagementToolException(INTERNAL_ERROR, e);
        }
    }
}
