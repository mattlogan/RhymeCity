package me.mattlogan.rhymecity.ui.module;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mattlogan.rhymecity.AppModule;
import me.mattlogan.rhymecity.ui.fragment.SearchFragment;
import me.mattlogan.rhymecity.ui.presenter.SearchPresenter;

@Module(
        injects = SearchFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public class SearchModule {

    private final SearchPresenter.SearchView searchView;

    public SearchModule(SearchPresenter.SearchView searchView) {
        this.searchView = searchView;
    }

    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(Bus bus) {
        return new SearchPresenter(searchView, bus);
    }
}
