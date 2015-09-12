package me.mattlogan.rhymecity.data;

import android.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;

import me.mattlogan.rhymecity.data.api.ApiService;
import me.mattlogan.rhymecity.data.model.Rhymes;
import me.mattlogan.rhymecity.data.model.RhymesResponse;
import retrofit.Response;
import rx.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DataModelTest {

    @Mock ApiService apiService;

    DataModel dataModel;

    @Before
    public void createDataModel() {
        initMocks(this);
        dataModel = new DataModel(apiService);
    }

    @Test
    public void testRhymesWithSuccess() {
        Response<RhymesResponse> response = Response.success(new RhymesResponse(new Rhymes(
                Arrays.asList("hi", "bye", "sigh"))));

        String word = "high";

        when(apiService.rhymes(word)).thenReturn(Observable.just(response));

        Pair<String, Response<RhymesResponse>> pair =
                dataModel.rhymes(word).toBlocking().single();

        // Pair's implementation is stripped because it's an Android class in a unit test,
        // so we'll just check to make sure it's not null since we can't get at its values.
        assertNotNull(pair);
    }

    @Test
    public void testRhymesWithNon200Response() {
        Response<RhymesResponse> response = Response.error(500, null);

        String word = "high";

        when(apiService.rhymes(word)).thenReturn(Observable.just(response));

        // Just making sure this doesn't throw
        dataModel.rhymes(word).toBlocking().single();
    }
}
