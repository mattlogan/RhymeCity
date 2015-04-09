package me.mattlogan.rhymecity.ui.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import me.mattlogan.rhymecity.event.rhymes.RhymesFailureEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesRequestedEvent;
import me.mattlogan.rhymecity.event.rhymes.RhymesSuccessEvent;

public class SearchPresenter {

    public interface SearchView {
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showRetrieveRhymesError();
        void showNoRhymesFoundError();
        void goToRhymesViewWithRhymes(List<String> rhymeList);
    }

    private final SearchView view;
    private final Bus bus;

    public SearchPresenter(SearchView view, Bus bus) {
        this.view = view;
        this.bus = bus;
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

    public void onRhymesForWordRequested(String word) {
        view.showLoadingIndicator();
        bus.post(new RhymesRequestedEvent(word));
    }

    @Subscribe
    public void onRhymesSuccess(RhymesSuccessEvent event) {
        view.hideLoadingIndicator();

        List<String> rhymeList = event.getRhymes();
        if (rhymeList.isEmpty()) {
            view.showNoRhymesFoundError();
        } else {
            view.goToRhymesViewWithRhymes(rhymeList);
        }
    }

    @Subscribe
    public void onRhymesFailure(RhymesFailureEvent event) {
        view.hideLoadingIndicator();
        view.showRetrieveRhymesError();
    }
}
