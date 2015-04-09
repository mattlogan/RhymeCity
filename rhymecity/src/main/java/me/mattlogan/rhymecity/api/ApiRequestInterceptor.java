package me.mattlogan.rhymecity.api;

import retrofit.RequestInterceptor;

public class ApiRequestInterceptor implements RequestInterceptor {

    private final String apiKey;

    public ApiRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Content-Type", "application/json");
        request.addHeader("X-Mashape-Key", apiKey);
    }
}
