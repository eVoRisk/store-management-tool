package com.store.management.tool.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.management.tool.config.client.SynchronousCallAdapterFactory;
import com.store.management.tool.exception.mapper.FakeStoreExceptionMapper;
import com.store.management.tool.exception.model.FakeStoreFailure;
import com.store.management.tool.service.client.FakeStoreApi;
import com.store.management.tool.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
public class FakeStoreConfig {

    private final FakeStoreConfigProperties properties;
    private final ObjectMapper objectMapper;

    @Bean
    public FakeStoreApi fakeStoreApi(final JsonUtil jsonUtil, final FakeStoreExceptionMapper exceptionMapper) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .callTimeout(properties.timeout(), TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(properties.baseUrl())
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(new SynchronousCallAdapterFactory<>(jsonUtil, exceptionMapper, FakeStoreFailure.class))
                .build();

        return retrofit.create(FakeStoreApi.class);
    }
}
