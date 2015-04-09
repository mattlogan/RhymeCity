package me.mattlogan.rhymecity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mattlogan.rhymecity.AppModule;
import me.mattlogan.rhymecity.api.ApiService;
import me.mattlogan.rhymecity.log.DebugLogger;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Module(
        overrides = true,
        addsTo = AppModule.class
)
public class DebugAppModule {

    @Provides
    @Singleton
    RestAdapter.Log provideDebugLogger() {
        return new DebugLogger();
    }

    @Provides
    @Singleton
    ApiService provideApiService(RequestInterceptor requestInterceptor, RestAdapter.Log logger,
                                 Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(logger)
                .build()
                .create(ApiService.class);
    }
}
