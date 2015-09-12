package me.mattlogan.rhymecity;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public final class RhymeCityApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraph();
    }

    private void buildObjectGraph() {
        objectGraph = ObjectGraph.create(new AppModule(getString(R.string.api_key)));
    }

    public ObjectGraph createScopedGraph(Object module) {
        return objectGraph.plus(module);
    }

    public static RhymeCityApplication get(Context context) {
        return (RhymeCityApplication) context.getApplicationContext();
    }
}
