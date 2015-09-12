package me.mattlogan.rhymecity.data.api;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public final class AuthInterceptor implements Interceptor {

    private final String apiKey;

    public AuthInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request authenticatedRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Mashape-Key", apiKey)
                .build();
        return chain.proceed(authenticatedRequest);
    }
}
