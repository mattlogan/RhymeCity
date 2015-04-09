package me.mattlogan.rhymecity.api;

import me.mattlogan.rhymecity.api.model.RhymesResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService {

    @GET("/words/{word}/rhymes")
    void getRhymes(@Path("word") String word, Callback<RhymesResponse> callback);
}
