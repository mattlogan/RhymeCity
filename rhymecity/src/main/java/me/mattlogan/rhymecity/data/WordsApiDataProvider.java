package me.mattlogan.rhymecity.data;

import me.mattlogan.rhymecity.data.api.ApiService;
import me.mattlogan.rhymecity.data.model.RhymesResponse;
import me.mattlogan.rhymecity.data.util.Pair;
import retrofit.Response;
import rx.Observable;
import rx.functions.Func1;

public final class WordsApiDataProvider implements DataProvider {

    private final ApiService apiService;

    public WordsApiDataProvider(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Pair<String, Response<RhymesResponse>>> rhymes(final String word) {
        return apiService.rhymes(word)
                .map(new Func1<Response<RhymesResponse>, Pair<String, Response<RhymesResponse>>>() {
                    @Override
                    public Pair<String, Response<RhymesResponse>> call(Response<RhymesResponse> rhymesResponse) {
                        return new Pair<>(word, rhymesResponse);
                    }
                });
    }
}
