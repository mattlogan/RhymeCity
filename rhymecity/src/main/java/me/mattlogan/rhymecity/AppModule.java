package me.mattlogan.rhymecity;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mattlogan.rhymecity.api.ApiEndpoint;
import me.mattlogan.rhymecity.api.ApiRequestInterceptor;
import me.mattlogan.rhymecity.api.ApiService;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Module(
        injects = RhymeCityApplication.class
)
public class AppModule {

    private final String apiKey;

    public AppModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor() {
        return new ApiRequestInterceptor(apiKey);
    }

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return new ApiEndpoint();
    }

    @Provides
    @Singleton
    ApiService provideApiService(RequestInterceptor requestInterceptor, Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(requestInterceptor)
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}
