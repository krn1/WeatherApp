package weather.repository.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "a8ea818749aab75c2eaca231e9071e8a";

    @Singleton
    @Provides
    public RestApi providesApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(RestApi.class);
    }
}
