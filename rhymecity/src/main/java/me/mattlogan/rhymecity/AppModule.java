package me.mattlogan.rhymecity;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mattlogan.rhymecity.data.DataModel;
import me.mattlogan.rhymecity.data.api.AuthInterceptor;
import me.mattlogan.rhymecity.data.api.ApiService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module(
        library = true
)
public final class AppModule {

    private final String apiKey;

    public AppModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new AuthInterceptor(apiKey));
        return client;
    }

    @Provides
    @Singleton
    ApiService provideApiService(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://wordsapiv1.p.mashape.com")
                .client(client)
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    DataModel provideDataModel(ApiService apiService) {
        return new DataModel(apiService);
    }
}
