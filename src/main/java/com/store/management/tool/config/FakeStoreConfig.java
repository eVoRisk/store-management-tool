package com.store.management.tool.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.management.tool.config.client.SynchronousCallAdapterFactory;
import com.store.management.tool.service.client.FakeStoreApi;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class FakeStoreConfig {

    private final FakeStoreConfigProperties properties;
    private final ObjectMapper objectMapper;

    public FakeStoreConfig(final FakeStoreConfigProperties properties, final ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Bean
    public FakeStoreApi fakeStoreApi() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .callTimeout(properties.timeout(), TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(properties.baseUrl())
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(new SynchronousCallAdapterFactory())
                .build();

        return retrofit.create(FakeStoreApi.class);
    }
}
