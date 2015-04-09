package me.mattlogan.rhymecity.api;

import retrofit.Endpoint;

public class ApiEndpoint implements Endpoint {

    @Override
    public String getUrl() {
        return "https://wordsapiv1.p.mashape.com";
    }

    @Override
    public String getName() {
        return "Production Endpoint";
    }
}
