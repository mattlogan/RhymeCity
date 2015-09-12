package me.mattlogan.rhymecity.data;

import me.mattlogan.rhymecity.data.model.RhymesResponse;
import me.mattlogan.rhymecity.data.util.Pair;
import retrofit.Response;
import rx.Observable;

public interface DataProvider {
    Observable<Pair<String, Response<RhymesResponse>>> rhymes(String word);
}
