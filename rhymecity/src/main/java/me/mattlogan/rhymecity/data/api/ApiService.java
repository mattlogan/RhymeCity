package me.mattlogan.rhymecity.data.api;

import me.mattlogan.rhymecity.data.model.RhymesResponse;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ApiService {
    @GET("/words/{word}/rhymes")
    Observable<Response<RhymesResponse>> rhymes(@Path("word") String word);
}
