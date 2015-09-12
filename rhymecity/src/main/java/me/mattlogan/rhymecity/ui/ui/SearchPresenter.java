package me.mattlogan.rhymecity.ui.ui;

import java.util.List;

import me.mattlogan.rhymecity.data.DataProvider;
import me.mattlogan.rhymecity.data.model.RhymesResponse;
import me.mattlogan.rhymecity.data.util.EndlessObserver;
import me.mattlogan.rhymecity.data.util.Pair;
import me.mattlogan.rhymecity.data.util.SafeCompositeSubscription;
import retrofit.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public final class SearchPresenter {

    public interface SearchView {
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showRetrieveRhymesError();
        void showNoRhymesFoundError(String word);
        void goToRhymesViewWithRhymes(String word, List<String> rhymeList);
    }

    private final SearchView view;
    private final DataProvider dataProvider;

    private final SafeCompositeSubscription subscriptions = new SafeCompositeSubscription();

    public SearchPresenter(SearchView view, DataProvider dataProvider) {
        this.view = view;
        this.dataProvider = dataProvider;
    }

    public void onPause() {
        subscriptions.clear();
    }

    public void onRhymesForWordRequested(String word) {
        view.showLoadingIndicator();
        subscriptions.add(dataProvider.rhymes(word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer));
    }

    private final Observer<Pair<String, Response<RhymesResponse>>> observer =
            new EndlessObserver<Pair<String, Response<RhymesResponse>>>() {
                @Override
                public void onError(Throwable e) {
                    view.hideLoadingIndicator();
                    view.showRetrieveRhymesError();
                }

                @Override
                public void onNext(Pair<String, Response<RhymesResponse>> pair) {
                    view.hideLoadingIndicator();

                    String word = pair.first;
                    Response<RhymesResponse> response = pair.second;

                    if (!response.isSuccess()) {
                        view.showRetrieveRhymesError();
                        return;
                    }

                    List<String> rhymes = response.body().rhymesObject().list();
                    if (rhymes.isEmpty()) {
                        view.showNoRhymesFoundError(word);
                    } else {
                        view.goToRhymesViewWithRhymes(word, rhymes);
                    }
                }
            };
}
