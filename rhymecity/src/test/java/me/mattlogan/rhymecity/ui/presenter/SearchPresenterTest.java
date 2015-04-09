package me.mattlogan.rhymecity.ui.presenter;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.mattlogan.rhymecity.event.rhymes.RhymesFailureEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesRequestedEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesSuccessEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class SearchPresenterTest {

    @Mock SearchPresenter.SearchView view;
    @Mock Bus bus;

    SearchPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchPresenter(view, bus);
    }

    @Test
    public void testOnResume() {
        presenter.onResume();
        verify(bus).register(presenter);
    }

    @Test
    public void testOnPause() {
        presenter.onPause();
        verify(bus).unregister(presenter);
    }

    @Test
    public void testOnRhymesForWordRequested() {
        presenter.onRhymesForWordRequested("test");

        verify(view).showLoadingIndicator();

        ArgumentCaptor<RhymesRequestedEvent> eventArgument =
                ArgumentCaptor.forClass(RhymesRequestedEvent.class);

        verify(bus).post(eventArgument.capture());

        assertEquals("test", eventArgument.getValue().getWord());
    }

    @Test
    public void testOnRhymesSuccessWithEmptyList() {
        presenter.onRhymesSuccess(new RhymesSuccessEvent("test", new ArrayList<String>()));
        verify(view).showNoRhymesFoundError(eq("test"));
    }

    @Test
    public void testOnRhymesSuccessWithNonEmptyList() {
        List<String> rhymeList = new ArrayList<>();
        rhymeList.add("rest");
        presenter.onRhymesSuccess(new RhymesSuccessEvent("test", rhymeList));

        verify(view).goToRhymesViewWithRhymes(eq("test"), eq(rhymeList));
    }

    @Test
    public void testOnRhymesFailure() {
        presenter.onRhymesFailure(new RhymesFailureEvent("test"));

        verify(view).hideLoadingIndicator();
        verify(view).showRetrieveRhymesError();
    }
}
