package me.mattlogan.rhymecity.api;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.mattlogan.rhymecity.api.model.RhymesObject;
import me.mattlogan.rhymecity.api.model.RhymesResponse;
import me.mattlogan.rhymecity.event.rhymes.RhymesFailureEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesRequestedEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesSuccessEvent;
import retrofit.Callback;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

public class ApiRequestHandlerTest {

    @Mock Bus bus;
    @Mock ApiService apiService;

    ApiRequestHandler apiRequestHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        apiRequestHandler = new ApiRequestHandler(bus, apiService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testOnRhymesRequestedWithSuccess() {
        apiRequestHandler.onRhymesRequested(new RhymesRequestedEvent("test"));

        ArgumentCaptor<Callback> callbackArgument =
                ArgumentCaptor.forClass(Callback.class);

        verify(apiService).getRhymes(eq("test"), callbackArgument.capture());

        List<String> rhymeList = new ArrayList<>();
        rhymeList.add("best");
        RhymesResponse response = new RhymesResponse(new RhymesObject(rhymeList));

        callbackArgument.getValue().success(response, null);

        ArgumentCaptor<RhymesSuccessEvent> eventArgument =
                ArgumentCaptor.forClass(RhymesSuccessEvent.class);

        verify(bus).post(eventArgument.capture());

        assertEquals(rhymeList, eventArgument.getValue().getRhymes());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testOnRhymesRequestedWithFailure() {
        apiRequestHandler.onRhymesRequested(new RhymesRequestedEvent("test"));

        ArgumentCaptor<Callback> callbackArgument =
                ArgumentCaptor.forClass(Callback.class);

        verify(apiService).getRhymes(eq("test"), callbackArgument.capture());

        callbackArgument.getValue().failure(null);

        verify(bus).post(isA(RhymesFailureEvent.class));
    }
}
