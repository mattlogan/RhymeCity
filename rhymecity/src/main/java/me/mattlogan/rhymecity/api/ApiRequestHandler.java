package me.mattlogan.rhymecity.api;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import me.mattlogan.rhymecity.api.model.RhymesResponse;
import me.mattlogan.rhymecity.event.rhymes.RhymesFailureEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesRequestedEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesSuccessEvent;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiRequestHandler {

    private final Bus bus;
    private final ApiService apiService;

    public ApiRequestHandler(Bus bus, ApiService apiService) {
        this.bus = bus;
        this.apiService = apiService;
    }

    @Subscribe
    public void onRhymesRequested(final RhymesRequestedEvent event) {
        final String word = event.getWord();

        apiService.getRhymes(word, new Callback<RhymesResponse>() {
            @Override
            public void success(RhymesResponse rhymesResponse, Response response) {
                bus.post(new RhymesSuccessEvent(word, rhymesResponse.getRhymesObject().getAll()));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new RhymesFailureEvent(word));
            }
        });
    }
}
