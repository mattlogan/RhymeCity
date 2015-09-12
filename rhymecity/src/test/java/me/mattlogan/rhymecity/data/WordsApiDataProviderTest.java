package me.mattlogan.rhymecity.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;

import me.mattlogan.rhymecity.data.api.ApiService;
import me.mattlogan.rhymecity.data.model.Rhymes;
import me.mattlogan.rhymecity.data.model.RhymesResponse;
import me.mattlogan.rhymecity.data.util.Pair;
import retrofit.Response;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WordsApiDataProviderTest {

    @Mock ApiService apiService;

    WordsApiDataProvider wordsApiDataProvider;

    @Before
    public void createDataModel() {
        initMocks(this);
        wordsApiDataProvider = new WordsApiDataProvider(apiService);
    }

    @Test
    public void testRhymesWithSuccess() {
        Response<RhymesResponse> response = Response.success(new RhymesResponse(new Rhymes(
                Arrays.asList("hi", "bye", "sigh"))));

        String word = "high";

        when(apiService.rhymes(word)).thenReturn(Observable.just(response));

        Pair<String, Response<RhymesResponse>> pair =
                wordsApiDataProvider.rhymes(word).toBlocking().single();

        assertNotNull(pair);
        assertEquals(word, pair.first);
        assertEquals(3, pair.second.body().rhymesObject().list().size());
    }

    @Test
    public void testRhymesWithNon200Response() {
        Response<RhymesResponse> response = Response.error(500, null);

        String word = "high";

        when(apiService.rhymes(word)).thenReturn(Observable.just(response));

        Pair<String, Response<RhymesResponse>> pair =
                wordsApiDataProvider.rhymes(word).toBlocking().single();

        assertNotNull(pair);
        assertFalse(pair.second.isSuccess());
    }
}
