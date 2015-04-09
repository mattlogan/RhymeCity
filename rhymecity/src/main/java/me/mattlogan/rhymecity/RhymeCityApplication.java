package me.mattlogan.rhymecity;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import dagger.ObjectGraph;
import me.mattlogan.rhymecity.api.ApiRequestHandler;
import me.mattlogan.rhymecity.api.ApiService;

public class RhymeCityApplication extends Application {

    private ObjectGraph objectGraph;

    @Inject Bus bus;
    @Inject ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
        createApiRequestHandler();
    }

    private void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(getString(R.string.api_key)));
        objectGraph.inject(this);
    }

    private void createApiRequestHandler() {
        bus.register(new ApiRequestHandler(bus, apiService));
    }

    public ObjectGraph createScopedGraph(Object module) {
        return objectGraph.plus(module);
    }

    public static RhymeCityApplication get(Context context) {
        return (RhymeCityApplication) context.getApplicationContext();
    }
}
