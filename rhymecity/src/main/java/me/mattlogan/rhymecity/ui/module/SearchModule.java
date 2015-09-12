package me.mattlogan.rhymecity.ui.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mattlogan.rhymecity.AppModule;
import me.mattlogan.rhymecity.data.DataModel;
import me.mattlogan.rhymecity.ui.fragment.SearchFragment;
import me.mattlogan.rhymecity.ui.ui.SearchPresenter;

@Module(
        injects = SearchFragment.class,
        addsTo = AppModule.class,
        complete = false
)
public final class SearchModule {

    private final SearchPresenter.SearchView searchView;

    public SearchModule(SearchPresenter.SearchView searchView) {
        this.searchView = searchView;
    }

    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(DataModel dataModel) {
        return new SearchPresenter(searchView, dataModel);
    }
}
